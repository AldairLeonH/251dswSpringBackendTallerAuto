/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Ciro
 */
@Embeddable
@Data  
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionServicioId implements Serializable {
    @Column(name = "cotizacion_id")       // Mapea al nombre exacto de la columna
    private Long cotizacionId;
    @Column(name = "servicio_id")       // Mapea al nombre exacto de la columna
    private Long servicioId;    
    
}
