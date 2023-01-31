package org.sindifisco.repository.contabil.balancete;

import org.sindifisco.model.Balancete;
import org.sindifisco.repository.filter.BalanceteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BalanceteRepositoryQuery {

	public Page<Balancete> filtrar(BalanceteFilter balanceteFilter, Pageable pageable);
}
