package org.sindifisco.repository.juridico.processo;
import org.sindifisco.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    Evento findByNome(String nome);

}
