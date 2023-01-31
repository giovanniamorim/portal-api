package org.sindifisco.repository.contabil.execucao;

import org.sindifisco.model.Execucao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecucaoRepository extends JpaRepository<Execucao, Long>, ExecucaoRepositoryQuery {


}
