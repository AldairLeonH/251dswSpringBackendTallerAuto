package dsw.tallerbackend.reporistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dsw.tallerbackend.model.TipoDocumento;
import java.util.Optional;

@Repository
public interface TipoDocumentoRepository  extends JpaRepository<TipoDocumento, Integer>{
    
    Optional<TipoDocumento> findByTipoDoc(String tipoDocumento); //nombre-cliente
    
}
