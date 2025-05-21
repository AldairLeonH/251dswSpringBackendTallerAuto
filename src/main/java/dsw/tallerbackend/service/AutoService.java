
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.AutoResponse;
import dsw.tallerbackend.model.Auto;
import dsw.tallerbackend.reporistory.AutoRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoService {
        @Autowired
    private AutoRepository autoRepository;
        
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
    
}
