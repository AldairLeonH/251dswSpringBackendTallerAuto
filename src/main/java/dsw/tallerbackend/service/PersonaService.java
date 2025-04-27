
package dsw.tallerbackend.service;

import dsw.tallerbackend.model.Persona;
import dsw.tallerbackend.reporistory.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;
    public List<Persona> getPersonas(){
        return personaRepository.findAll();
    }
     
}
