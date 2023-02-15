package org.sindifisco.repository.contabil.execucao;

import org.sindifisco.model.Execucao;
import org.sindifisco.repository.filter.ExecucaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExecucaoRepositoryQuery {

	public Page<Execucao> filtrar(ExecucaoFilter execucaoFilter, Pageable pageable);
}