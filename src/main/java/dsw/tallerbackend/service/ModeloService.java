
package dsw.tallerbackend.service;

import dsw.tallerbackend.model.Modelo;
import dsw.tallerbackend.reporistory.ModeloRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class ModeloService {
    @Autowired private ModeloRepository modeloRepo;
    
    public List<Modelo> findAll() {
        return modeloRepo.findAll();
    }
}
