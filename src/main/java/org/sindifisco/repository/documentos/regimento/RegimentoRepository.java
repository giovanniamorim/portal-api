package org.sindifisco.repository.documentos.regimento;

import org.sindifisco.model.Regimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegimentoRepository extends JpaRepository<Regimento, Long>, RegimentoRepositoryQuery {


}
