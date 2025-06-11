
package dsw.tallerbackend.reporistory;

import dsw.tallerbackend.model.OrdenPregunta;
import dsw.tallerbackend.model.OrdenPreguntaPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdenPreguntaRepository extends JpaRepository<OrdenPregunta, OrdenPreguntaPK> {

    public void deleteByIdIdOst(int id);
    List<OrdenPregunta> findByOstIdOst(Integer idOst);
}
