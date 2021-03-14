package org.sindifisco.portal.api.repository.contabil;

import org.sindifisco.portal.api.entity.contabil.PlanoConta;
import org.sindifisco.portal.api.repository.filter.PlanoContaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanoContaRepositoryQuery {
	
	public Page<PlanoConta> filtrar(PlanoContaFilter planoContaFilter, Pageable pageable);

}
