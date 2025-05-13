
package dsw.tallerbackend.service;

import dsw.tallerbackend.model.TipoDocumento;
import dsw.tallerbackend.reporistory.TipoDocumentoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class TipoDocumentoService {
    @Autowired
    TipoDocumentoRepository TipoDocumentoRepository;
    public List<TipoDocumento> getTipoDocumentos(){
        return TipoDocumentoRepository.findAll();
    }
    
}
