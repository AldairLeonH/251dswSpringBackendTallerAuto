
package dsw.tallerbackend.reporistory;

import dsw.tallerbackend.model.Pregunta;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {

    public Optional<Pregunta> findById(Integer idPregunta);
}