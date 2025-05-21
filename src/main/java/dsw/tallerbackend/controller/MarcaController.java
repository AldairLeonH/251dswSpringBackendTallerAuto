package dsw.tallerbackend.controller;

import dsw.tallerbackend.model.Marca;
import dsw.tallerbackend.service.MarcaService;
import dsw.tallerbackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/marca")
public class MarcaController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    MarcaService marcaService;
    
    @GetMapping
    public List<Marca> getMarcas() {
        return marcaService.findAll();
    }
}