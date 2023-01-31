package org.sindifisco.repository.contabil.planejamento;

import org.sindifisco.model.Planejamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanejamentoRepository extends JpaRepository<Planejamento, Long>, PlanejamentoRepositoryQuery {


}
