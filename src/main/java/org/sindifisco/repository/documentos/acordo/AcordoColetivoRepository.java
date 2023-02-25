package org.sindifisco.repository.documentos.acordo;

import org.sindifisco.model.AcordoColetivo;
import org.sindifisco.model.Regimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcordoColetivoRepository extends JpaRepository<AcordoColetivo, Long>, AcordoColetivoRepositoryQuery {


}
