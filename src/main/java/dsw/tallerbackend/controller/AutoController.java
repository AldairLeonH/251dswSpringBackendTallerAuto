/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.controller;

import dsw.tallerbackend.dto.AutoResponse;
import dsw.tallerbackend.reporistory.PersonaRepository;
import dsw.tallerbackend.service.AutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auto")
public class AutoController {
    
        @Autowired
    private AutoService autoService;
    @Autowired
    PersonaRepository personaRepository;
        @GetMapping("/autos/persona/{idPersona}")
    public List<AutoResponse> getAutosPorPersona(@PathVariable Integer idPersona) {
        return autoService.findByPersona(idPersona);
    }
}
