package org.sindifisco.repository.contabil.balanco;

import org.sindifisco.model.Balanco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalancoRepository extends JpaRepository<Balanco, Long>, BalancoRepositoryQuery {


}
