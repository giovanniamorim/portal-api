package org.sindifisco.repository.contabil.planejamento;

import org.sindifisco.model.Planejamento;
import org.sindifisco.repository.filter.PlanejamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanejamentoRepositoryQuery {

	public Page<Planejamento> filtrar(PlanejamentoFilter planejamentoFilter, Pageable pageable);
}
