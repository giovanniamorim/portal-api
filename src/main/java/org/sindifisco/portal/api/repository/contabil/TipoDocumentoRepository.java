package org.sindifisco.portal.api.repository.contabil;

import org.sindifisco.portal.api.entity.contabil.TipoDocumento;
import org.sindifisco.portal.api.repository.filter.TipoDocumentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {
	
	Page<TipoDocumento> filtrar(TipoDocumentoFilter tipoDocumentoFilter, Pageable pageable);
	
}

