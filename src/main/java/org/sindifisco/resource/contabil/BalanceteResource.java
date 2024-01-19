package org.conefisco.resource.contabil;


import org.conefisco.model.Balancete;
import org.conefisco.repository.contabil.balancete.BalanceteRepository;
import org.conefisco.repository.filter.BalanceteFilter;
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
@RequestMapping("/api/balancetes")
@Slf4j
public class BalanceteResource {
    @Autowired
    BalanceteRepository balanceteRepository;


    @PostMapping
    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('ROLE_CREATE') and #oauth2.hasScope('write')")
    public Balancete addBalancete(@RequestBody @Valid Balancete balancete)  {
        return balanceteRepository.save(balancete);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public Page<Balancete> pesquisar(
            BalanceteFilter balanceteFilter,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return balanceteRepository.filtrar(balanceteFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public Balancete findBalanceteById(@PathVariable Long id) {
        return balanceteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Balancete não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE') and #oauth2.hasScope('write')")
    public void deleteBalancete(@PathVariable Long id) {
        balanceteRepository.findById(id).map(user -> {
            balanceteRepository.delete(user);
            return TYPE;
        }).orElseThrow(() -> new ResponseStatusException(
                NOT_FOUND, "Balancete não encontrado"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE') and #oauth2.hasScope('write')")
    public ResponseEntity<Balancete> editBalancete(@PathVariable Long id, @Valid @RequestBody Balancete newBalancete){
               return balanceteRepository.findById(id)
                .map(balancete -> {
                    balancete.setAno(newBalancete.getAno());
                    balancete.setMes(newBalancete.getMes());
                    balancete.setFileUrl((newBalancete.getFileUrl()));
                    Balancete balanceteUpdated = balanceteRepository.save(balancete);
                    return ResponseEntity.ok().body(balanceteUpdated);
                }).orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Balancete não encontrado"));
    }


}
