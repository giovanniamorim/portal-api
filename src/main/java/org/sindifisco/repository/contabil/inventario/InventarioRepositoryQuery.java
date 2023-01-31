package org.sindifisco.repository.contabil.inventario;

import org.sindifisco.model.Inventario;
import org.sindifisco.repository.filter.InventarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventarioRepositoryQuery {

	public Page<Inventario> filtrar(InventarioFilter inventarioFilter, Pageable pageable);
}
