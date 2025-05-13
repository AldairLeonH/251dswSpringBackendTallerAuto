
package dsw.tallerbackend.service;

import dsw.tallerbackend.model.TipoDocumento;
import dsw.tallerbackend.reporistory.TipoDocumentoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TipoDocumentoService {
    TipoDocumentoRepository TipoDocumentoRepository;
    public List<TipoDocumento> getTipoDocumentos(){
        return TipoDocumentoRepository.findAll();
    }
    
}
