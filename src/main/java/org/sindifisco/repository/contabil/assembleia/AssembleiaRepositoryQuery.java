package org.sindifisco.repository.contabil.assembleia;

import org.sindifisco.model.Assembleia;
import org.sindifisco.repository.filter.AssembleiaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssembleiaRepositoryQuery {

	public Page<Assembleia> filtrar(AssembleiaFilter assembleiaFilter, Pageable pageable);
}
