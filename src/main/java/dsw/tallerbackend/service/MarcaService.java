
package dsw.tallerbackend.service;

import dsw.tallerbackend.model.Marca;
import dsw.tallerbackend.reporistory.MarcaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {


    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }
    
}
