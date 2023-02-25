package org.sindifisco.repository.documentos.acordo;

import org.sindifisco.model.AcordoColetivo;
import org.sindifisco.repository.filter.AcordoColetivoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcordoColetivoRepositoryQuery {

	public Page<AcordoColetivo> filtrar(AcordoColetivoFilter acordoFilter, Pageable pageable);
}
