package org.sindifisco.repository.lancamento;

import org.sindifisco.model.Lancamento;
import org.sindifisco.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
