
package dsw.tallerbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data  
@NoArgsConstructor
@AllArgsConstructor
public class OrdenPreguntaPK implements Serializable {
    
    @Column(name = "id_ost")       // Mapea al nombre exacto de la columna
    private Integer idOst;

    @Column(name = "id_pregunta")  // Mapea al nombre exacto de la columna
    private Integer idPregunta;
}