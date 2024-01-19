package org.conefisco.repository.contabil.contrato;

import org.conefisco.model.Contrato;
import org.conefisco.repository.filter.ContratoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContratoRepositoryQuery {

	public Page<Contrato> filtrar(ContratoFilter contratoFilter, Pageable pageable);
}
