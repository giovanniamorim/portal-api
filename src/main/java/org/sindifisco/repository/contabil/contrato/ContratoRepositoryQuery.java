package org.sindifisco.repository.contabil.contrato;

import org.sindifisco.model.Contrato;
import org.sindifisco.repository.filter.ContratoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContratoRepositoryQuery {

	public Page<Contrato> filtrar(ContratoFilter contratoFilter, Pageable pageable);
}
