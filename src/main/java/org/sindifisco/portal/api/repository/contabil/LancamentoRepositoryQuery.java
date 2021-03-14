package org.sindifisco.portal.api.repository.contabil;

import org.sindifisco.portal.api.entity.contabil.Lancamento;
import org.sindifisco.portal.api.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
