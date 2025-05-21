
package dsw.tallerbackend.dto;

import dsw.tallerbackend.model.Auto;
import dsw.tallerbackend.model.Ost;
import dsw.tallerbackend.model.TipoEstado;
import dsw.tallerbackend.model.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Ciro
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OstResponseDTO {
    private int idOst;
    private LocalDate fecha;
    private LocalTime hora;
    private String direccion;
    private TipoEstado estado;
    private Auto auto;
    private Usuario recepcionista;
    
     public static OstResponseDTO fromEntity(Ost ost) {
         return OstResponseDTO.builder()
                 .idOst(ost.getIdOst())
                 .fecha(ost.getFecha())
                 .hora(ost.getHora())
                 .direccion(ost.getDireccion())
                 .estado(ost.getEstado())
                 .auto(ost.getAuto())
                 .recepcionista(ost.getRecepcionista())
                 .build();
         
     }
     public static List<OstResponseDTO> fromEntities(List<Ost> osts) {
         return osts.stream()
                 .map(OstResponseDTO::fromEntity)
                 .collect(Collectors.toList());
         
     }    

     
     
    
}
