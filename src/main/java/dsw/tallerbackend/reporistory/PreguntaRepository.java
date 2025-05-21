
package dsw.tallerbackend.reporistory;

import dsw.tallerbackend.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {}