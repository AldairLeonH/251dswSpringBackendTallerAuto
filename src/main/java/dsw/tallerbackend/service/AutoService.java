
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.AutoRequest;
import dsw.tallerbackend.dto.AutoResponse;
import dsw.tallerbackend.model.Auto;
import dsw.tallerbackend.model.Modelo;
import dsw.tallerbackend.model.Persona;
import dsw.tallerbackend.reporistory.AutoRepository;
import dsw.tallerbackend.reporistory.ModeloRepository;
import dsw.tallerbackend.reporistory.PersonaRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoService {
    @Autowired
    private AutoRepository autoRepository;
    @Autowired
    ModeloRepository modeloRepository;
    @Autowired
    PersonaRepository personaRepository;
        
    public List<AutoResponse> findByPersona(Integer idPersona) {
        List<Auto> autos = autoRepository.findByPersona_IdPersona(idPersona);
        System.out.println(Arrays.toString(autos.toArray()));
        return autos.stream()
                 .map(auto -> AutoResponse.builder()
                        .idAuto(auto.getIdAuto())
                        .placa(auto.getPlaca())
                        .modelo(auto.getModelo())
                        .anio(auto.getAnio())
                        .color(auto.getColor())
                        .build()
                )
                .collect(Collectors.toList());
    }
    public AutoResponse insertAuto(AutoRequest autoRequest){
        Integer idModelo = autoRequest.getIdModelo();
        Modelo modelo = modeloRepository.findById(idModelo).get();
        if(modelo==null) return new AutoResponse();
        
        Integer idPersona = autoRequest.getIdPersona();
        Persona persona =personaRepository.findById(idPersona).get();
        if(persona==null) return new AutoResponse();
        
        Auto auto = new Auto(
                autoRequest.getIdAuto(),
                autoRequest.getPlaca(),
                modelo,
                persona,
                autoRequest.getAnio(),
                autoRequest.getColor()     
        );
        auto=autoRepository.save(auto);
        return AutoResponse.fromEntity(auto);
    
    }
    public AutoResponse updateAuto(AutoRequest autoRequest){
        Integer idModelo = autoRequest.getIdModelo();
        Modelo modelo = modeloRepository.findById(idModelo).get();
        if(modelo==null) return new AutoResponse();
        
        Integer idPersona = autoRequest.getIdPersona();
        Persona persona =personaRepository.findById(idPersona).get();
        if(persona==null) return new AutoResponse();
        
        Auto auto = new Auto(
                autoRequest.getIdAuto(),
                autoRequest.getPlaca(),
                modelo,
                persona,
                autoRequest.getAnio(),
                autoRequest.getColor()       
        );
        auto=autoRepository.save(auto);
        return AutoResponse.fromEntity(auto);
    
    }
    public void deleteAuto(Integer id){
        autoRepository.deleteById(id);
        
    }
    public AutoResponse findAuto(Integer id){
        Optional<Auto> result=autoRepository.findById(id);
        if(!result.isPresent())
            return null;
        return AutoResponse.fromEntity(result.get());
        
        
    }  

}
