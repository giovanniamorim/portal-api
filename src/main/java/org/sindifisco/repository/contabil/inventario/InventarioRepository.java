package org.sindifisco.repository.contabil.inventario;

import org.sindifisco.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long>, InventarioRepositoryQuery {


}
