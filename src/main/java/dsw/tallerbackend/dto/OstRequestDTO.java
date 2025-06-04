
package dsw.tallerbackend.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OstRequestDTO{
    private Integer idOst;
    private LocalDate fecha;
    private LocalTime hora;
    private Integer idDireccion;
    
    private Integer idAuto;
    private Integer anio;
    private String placa;
    private Integer idModelo;
    private Integer idPersona;
    private String color;
    
    private Integer idEstado;
    private Integer IdRecepcionista;
    private List<Integer> preguntas;
}
