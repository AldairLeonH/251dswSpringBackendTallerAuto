/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.controller;

import dsw.tallerbackend.dto.MaterialResponse;
import dsw.tallerbackend.model.Material;
import dsw.tallerbackend.reporistory.MaterialRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/materiales")
public class MaterialController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping
    public List<MaterialResponse> getMateriales() {
        List<Material> materiales = materialRepository.findAll();
        logger.info("Listando todos los materiales: {}", materiales.size());
        return MaterialResponse.fromEntities(materiales);
    }    
}
