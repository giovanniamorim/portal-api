package org.conefisco.repository.contabil.inventario;

import org.conefisco.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long>, InventarioRepositoryQuery {


}
