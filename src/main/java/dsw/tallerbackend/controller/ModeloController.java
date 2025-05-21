package dsw.tallerbackend.controller;

import dsw.tallerbackend.model.Modelo;
import dsw.tallerbackend.reporistory.ModeloRepository;
import dsw.tallerbackend.service.ModeloService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/modelo")
public class ModeloController {       

    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    
    @Autowired private ModeloService modeloService;
    
    @GetMapping
    public List<Modelo> getModelos() {
        return modeloService.findAll();
    }
}
