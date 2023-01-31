package org.sindifisco.repository.contabil.balanco;

import org.sindifisco.model.Balanco;
import org.sindifisco.repository.filter.BalancoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BalancoRepositoryQuery {

	public Page<Balanco> filtrar(BalancoFilter balancoFilter, Pageable pageable);
}
