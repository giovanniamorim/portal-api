package org.sindifisco.resource.contabil;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.sindifisco.message.ResponseMessage;
import org.sindifisco.model.FileDB;
import org.sindifisco.model.Lancamento;
import org.sindifisco.repository.fileDB.FileDBRepository;
import org.sindifisco.repository.lancamento.LancamentoRepository;
import org.sindifisco.repository.contabil.planoContas.PlanoContasRepository;
import lombok.extern.slf4j.Slf4j;
import org.sindifisco.repository.filter.LancamentoFilter;
import org.sindifisco.service.FileDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Table;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

import static java.lang.Void.TYPE;
import static org.springframework.http.HttpStatus.*;

@RestController
@Table(name = "ctb_lancamento")
@RequestMapping("/api/lancamentos")
@Slf4j
public class LancamentoResource {
    @Autowired
    LancamentoRepository lancamentoRepository;
    Pageable unpaged = Pageable.unpaged();


    @Autowired
    private PlanoContasRepository planoContasRepository;

    @Autowired
    private FileDBService fileDBService;

    @Autowired
    private FileDBRepository fileDBRepository;

    @PostMapping()
    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('ROLE_CREATE') and #oauth2.hasScope('write')")
    public Lancamento addLancamento(@RequestBody @Valid Lancamento lancamento) {
        return lancamentoRepository.save(lancamento);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public Lancamento findById(@PathVariable Long id) {
        return lancamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Lançamento não encontrado"));
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public Page<Lancamento> getAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable){
        return lancamentoRepository.findAll(pageable);
    }


    @GetMapping("/receitas")
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public Page<Lancamento> getAllReceitas(
             Pageable pageable){
        return lancamentoRepository.findByTipoLancamento("Receita", pageable);
    }


    @GetMapping("/despesas")
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public Page<Lancamento> getAllDespesas(
            @PageableDefault(page = 0, size = 5, sort = "dataLancamento, desc", direction = Sort.Direction.DESC) Pageable pageable){
        return lancamentoRepository.findByTipoLancamento("Despesa", pageable);
    }

    @GetMapping("/busca")
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public ResponseEntity<BuscaAvancadaResponse> buscaAvancada(
            @PageableDefault(page = 0, size = 5, sort = "dataLancamento", direction = Sort.Direction.DESC)
            LancamentoFilter lancamentoFilter, Pageable pageable) {

        Page<Lancamento> lancamentos = lancamentoRepository.filtrar(lancamentoFilter, pageable);
        Double totalValor = lancamentoRepository.sumValorByFilter(lancamentoFilter);

        BuscaAvancadaResponse response = new BuscaAvancadaResponse(lancamentos, totalValor);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/relatorio")
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public ResponseEntity<byte[]> relatorio(LancamentoFilter lancamentoFilter) throws JRException {

        List<Lancamento> lancamentos = lancamentoRepository.filtrarRelatorio(lancamentoFilter);

        // Carregue o template do relatório
        InputStream jasperStream = getClass().getResourceAsStream("/reports/lancamentos.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        // Preencha o relatório com os dados simulados
        JasperPrint report = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(lancamentos));

        // Exporte o relatório para PDF
        byte[] data = JasperExportManager.exportReportToPdf(report);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=lancamentos.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE') and #oauth2.hasScope('write')")
    public ResponseEntity<String> deleteLancamento(@PathVariable Long id) {
        try {
            // Verificar se o lançamento existe
            Lancamento lancamento = lancamentoRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Lançamento não encontrado"));

            // Deletar o arquivo associado ao lançamento, caso exista
            String urlArquivo = lancamento.getFileUrl();
            if (urlArquivo != null && urlArquivo.contains("=")) {
                String nomeArquivo = urlArquivo.substring(urlArquivo.lastIndexOf("=") + 1);
                FileDB existingFile = fileDBRepository.findByName(nomeArquivo);
                if (existingFile != null) {
                    fileDBService.delete(existingFile);
                }
            }

            // Deletar o lançamento
            lancamentoRepository.delete(lancamento);

            return ResponseEntity.noContent().build(); // Resposta indicando sucesso
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // Resposta indicando que o lançamento não foi encontrado
        } catch (Exception e) {
            String message = "Ocorreu um erro ao deletar o lançamento.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message); // Resposta indicando erro interno
        }
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE') and #oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> updateLancamento(@PathVariable Long id, @RequestBody Lancamento updatedLancamento) {
        return lancamentoRepository.findById(id)
                .map(lancamento -> {
                    lancamento.setTipoLancamento(updatedLancamento.getTipoLancamento());

                    // Converter a data para UTC
                    LocalDate utcDate = updatedLancamento.getDataLancamento();
                    ZonedDateTime utcDateTime = utcDate.atStartOfDay(ZoneOffset.UTC).toOffsetDateTime().toZonedDateTime();
                    lancamento.setDataLancamento(utcDateTime.toLocalDate());

                    lancamento.setPlanoConta(updatedLancamento.getPlanoConta());
                    lancamento.setValor((updatedLancamento.getValor()));
                    lancamento.setModoPagamento(updatedLancamento.getModoPagamento());
                    lancamento.setTipoComprovante(updatedLancamento.getTipoComprovante());
                    lancamento.setNumDoc(updatedLancamento.getNumDoc());
                    lancamento.setNumCheque(updatedLancamento.getNumCheque());
                    lancamento.setObs(updatedLancamento.getObs());
                    lancamento.setSupCaixa(updatedLancamento.getSupCaixa());
                    lancamento.setAnoExercicio(updatedLancamento.getAnoExercicio());
                    lancamento.setFileUrl(updatedLancamento.getFileUrl());

                    Lancamento putLancamento = lancamentoRepository.save(lancamento);

                    return ResponseEntity.ok().body(putLancamento);
                }).orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Lançamento não encontrado"));
    }



}