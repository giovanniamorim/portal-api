package org.sindifisco.repository.juridico.processo;
import org.sindifisco.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    Processo findByNumero(String numero);
}
