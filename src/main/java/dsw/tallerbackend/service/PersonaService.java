
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.PersonaRequest;
import dsw.tallerbackend.dto.PersonaResponse;
import dsw.tallerbackend.model.Persona;
import dsw.tallerbackend.model.TipoDocumento;
import dsw.tallerbackend.reporistory.PersonaRepository;
import dsw.tallerbackend.reporistory.TipoDocumentoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonaService{

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    
    public List<PersonaResponse> listPersonas(){
        return PersonaResponse.fromEntities(personaRepository.findAll());
    }
    public PersonaResponse insertPersona(PersonaRequest personaRequest) {
        Integer idTipoDoc = personaRequest.getIdTipoDoc();
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(idTipoDoc).get();
        if(tipoDocumento==null) return new PersonaResponse();
        
        
        Persona persona = new Persona(
                personaRequest.getIdPersona(),
                personaRequest.getNroDocumento(),
                tipoDocumento,
                personaRequest.getApellidoPaterno(),
                personaRequest.getApellidoMaterno(),
                personaRequest.getNombres(),
                personaRequest.getDireccion(),
                personaRequest.getSexo(),
                personaRequest.getTelefono()
                 
        );
        persona=personaRepository.save(persona);
        return PersonaResponse.fromEntity(persona);
    }
    public PersonaResponse updatePersona(PersonaRequest personaRequest) {
        Integer idTipoDoc = personaRequest.getIdTipoDoc();
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(idTipoDoc).get();
        if(tipoDocumento==null) return new PersonaResponse();
        
        
        Persona persona = new Persona(
                personaRequest.getIdPersona(),
                personaRequest.getNroDocumento(),
                tipoDocumento,
                personaRequest.getApellidoPaterno(),
                personaRequest.getApellidoMaterno(),
                personaRequest.getNombres(),
                personaRequest.getDireccion(),
                personaRequest.getSexo(),
                personaRequest.getTelefono()
                 
        );
        persona=personaRepository.save(persona);
        return PersonaResponse.fromEntity(persona);        


    }
    public void deletePersona(int id) {
        if (!personaRepository.existsById(id)) {
            throw new RuntimeException("Persona no encontrada");
        }
        personaRepository.deleteById(id);
    }
    public PersonaResponse findPersona(Integer id){
        Optional<Persona> result=personaRepository.findById(id);
        if(!result.isPresent())
            return null;
        return PersonaResponse.fromEntity(result.get());
        
        
    }    
     
}