package org.sindifisco.portal.api.repository.contabil;

import org.sindifisco.portal.api.entity.contabil.PlanoConta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoContaRepository extends JpaRepository<PlanoConta, Integer>, PlanoContaRepositoryQuery{

}
