package org.sindifisco.repository.contabil.assembleia;

import org.sindifisco.model.Assembleia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssembleiaRepository extends JpaRepository<Assembleia, Long>, AssembleiaRepositoryQuery {


}
