package org.sindifisco.portal.api.repository.contabil;


import org.sindifisco.portal.api.entity.contabil.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LancamentoRepository extends JpaRepository<Lancamento, Integer>, LancamentoRepositoryQuery {

}

