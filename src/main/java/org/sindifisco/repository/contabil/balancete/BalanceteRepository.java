package org.sindifisco.repository.contabil.balancete;

import org.sindifisco.model.Balancete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceteRepository extends JpaRepository<Balancete, Long>, BalanceteRepositoryQuery {


}
