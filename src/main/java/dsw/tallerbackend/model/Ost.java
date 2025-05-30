
package dsw.tallerbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // permite realizar codigo limpio
@Entity//permite realizat las operaciones CRUD
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orden_servicio_tecnico")
public class Ost {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name="id_ost")
    private int idOst;
    
       
    private LocalDate fecha;
    
    
    private LocalTime hora;
    
    
    
    private String direccion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    private TipoEstado estado;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_auto", referencedColumnName = "id_auto")
    private Auto auto;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_recepcionista", referencedColumnName = "id_usuario")
    private Usuario recepcionista;
    
    

}
