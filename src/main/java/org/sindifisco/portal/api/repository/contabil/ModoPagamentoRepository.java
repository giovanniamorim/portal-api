package org.sindifisco.portal.api.repository.contabil;

import org.sindifisco.portal.api.entity.contabil.ModoPagamento;
import org.sindifisco.portal.api.repository.filter.ModoPagamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ModoPagamentoRepository extends JpaRepository<ModoPagamento, Integer>, ModoPagamentoRepositoryQuery {
	
	Page<ModoPagamento> filtrar(ModoPagamentoFilter modoPagamentoFilter, Pageable pageable);

}
