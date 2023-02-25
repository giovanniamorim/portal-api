package org.sindifisco.repository.documentos.regimento;

import org.sindifisco.model.Regimento;
import org.sindifisco.repository.filter.RegimentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegimentoRepositoryQuery {

	public Page<Regimento> filtrar(RegimentoFilter balanceteFilter, Pageable pageable);
}
