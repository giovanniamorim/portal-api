package org.conefisco.resource.contabil;


import org.conefisco.model.Lancamento;
import org.conefisco.repository.LancamentoRepository;
import org.conefisco.repository.contabil.planoContas.PlanoContasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Table;
import javax.validation.Valid;

import static java.lang.Void.TYPE;
import static org.springframework.http.HttpStatus.*;

@RestController
@Table(name = "ctb_lancamento")
@RequestMapping("/api/lancamentos")
@Slf4j
public class LancamentoResource {
    @Autowired
    LancamentoRepository lancamentoRepository;

    @Autowired
    private PlanoContasRepository planoContasRepository;

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


    @GetMapping("/receitas")
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public Page<Lancamento> getAllReceitas(
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return lancamentoRepository.findByTipoLancamento("Receita", pageable);
    }

    @GetMapping("/despesas")
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public Page<Lancamento> getAllDespesas(
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return lancamentoRepository.findByTipoLancamento("Despesa", pageable);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE') and #oauth2.hasScope('write')")
    public void deleteLancamento(@PathVariable Long id) {
        lancamentoRepository.findById(id).map(lancamento -> {
            lancamentoRepository.delete(lancamento);
            return TYPE;
        }).orElseThrow(() -> new ResponseStatusException(
                NOT_FOUND, "Lançamento não encontrado"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE') and #oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> updateLancamento(@PathVariable Long id, @RequestBody Lancamento updatedLancamento) {
        return lancamentoRepository.findById(id)
                .map(lancamento -> {
                    lancamento.setTipoLancamento(updatedLancamento.getTipoLancamento());
                    lancamento.setDataLancamento(updatedLancamento.getDataLancamento());
                    lancamento.setPlanoConta(updatedLancamento.getPlanoConta());
                    lancamento.setValor((updatedLancamento.getValor()));
                    lancamento.setModoPagamento(updatedLancamento.getModoPagamento());
                    lancamento.setTipoComprovante(updatedLancamento.getTipoComprovante());
                    lancamento.setObs(updatedLancamento.getObs());
                    lancamento.setSupCaixa(updatedLancamento.getSupCaixa());
                    lancamento.setFileUrl(updatedLancamento.getFileUrl());

                    Lancamento putLancamento = lancamentoRepository.save(lancamento);

                    return ResponseEntity.ok().body(putLancamento);
                }).orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Lançamento não encontrado"));
    }

}