
package dsw.tallerbackend.reporistory;

import dsw.tallerbackend.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    
        boolean existsByNroDocumento(String nroDocumento);
}
