
package dsw.tallerbackend.model;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OstTecnicoId implements Serializable {
    private Long idOst;
    private Long idTecnico;
    
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OstTecnicoId)) return false;
        OstTecnicoId that = (OstTecnicoId) o;
        return Objects.equals(idTecnico, that.idTecnico) &&
               Objects.equals(idOst, that.idOst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOst, idTecnico);
    }
}