package org.sindifisco.repository.contabil.planoContas;

import org.sindifisco.model.PlanoContas;
import org.sindifisco.repository.filter.PlanoContasFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanoContasRepositoryQuery {

	public Page<PlanoContas> filtrar(PlanoContasFilter planoContasFilter, Pageable pageable);
}
