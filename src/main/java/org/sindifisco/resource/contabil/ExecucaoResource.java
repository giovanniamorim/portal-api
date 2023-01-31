package org.sindifisco.resource.contabil;


import org.sindifisco.model.Execucao;
import org.sindifisco.repository.contabil.execucao.ExecucaoRepository;
import org.sindifisco.repository.filter.ExecucaoFilter;
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

import javax.validation.Valid;

import static java.lang.Void.TYPE;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/execucoes")
@Slf4j
public class ExecucaoResource {
    @Autowired
    ExecucaoRepository execucaoRepository;

    @PostMapping
    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('ROLE_CREATE') and #oauth2.hasScope('write')")
    public Execucao addExecucao(@RequestBody @Valid Execucao execucao)  {
        return execucaoRepository.save(execucao);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public Page<Execucao> pesquisar(
            ExecucaoFilter execucaoFilter,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return execucaoRepository.filtrar(execucaoFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public Execucao findExecucaoById(@PathVariable Long id) {
        return execucaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Execucao não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE') and #oauth2.hasScope('write')")
    public void deleteExecucao(@PathVariable Long id) {
        execucaoRepository.findById(id).map(user -> {
            execucaoRepository.delete(user);
            return TYPE;
        }).orElseThrow(() -> new ResponseStatusException(
                NOT_FOUND, "Execucao não encontrado"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE') and #oauth2.hasScope('write')")
    public ResponseEntity<Execucao> editExecucao(@PathVariable Long id, @Valid @RequestBody Execucao newExecucao){
               return execucaoRepository.findById(id)
                .map(execucao -> {
                    execucao.setAno(newExecucao.getAno());
                    execucao.setDescricao(newExecucao.getDescricao());
                    execucao.setFileUrl((newExecucao.getFileUrl()));
                    Execucao execucaoUpdated = execucaoRepository.save(execucao);
                    return ResponseEntity.ok().body(execucaoUpdated);
                }).orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Execucao não encontrado"));
    }

}
