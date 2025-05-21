
package dsw.tallerbackend.dto;

import dsw.tallerbackend.model.Modelo;
import dsw.tallerbackend.model.Persona;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutoResponse {
    private Integer idAuto;
    private String placa;
    private Modelo modelo;
    private Integer anio;
    private String color;
    private Persona persona;
}
