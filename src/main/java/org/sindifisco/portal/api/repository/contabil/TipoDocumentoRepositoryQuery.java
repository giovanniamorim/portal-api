package org.sindifisco.portal.api.repository.contabil;

import org.sindifisco.portal.api.entity.contabil.TipoDocumento;
import org.sindifisco.portal.api.repository.filter.TipoDocumentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TipoDocumentoRepositoryQuery {
	
	public Page<TipoDocumento> filtrar(TipoDocumentoFilter tipoDocumentoFilter, Pageable pageable);

}
