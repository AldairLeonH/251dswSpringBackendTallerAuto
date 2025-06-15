/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.dto;

import dsw.tallerbackend.model.Cotizacion;
import dsw.tallerbackend.model.Ost;
import java.time.LocalDate;
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
public class CotizacionResponse {
    private Long id;
    private LocalDate fecha;
    private Double total;
    private Ost ost;
    public static CotizacionResponse fromEntity(Cotizacion cotizacion) {
        return CotizacionResponse.builder()
                .id(cotizacion.getId())
                .fecha(cotizacion.getFecha())
                .total(cotizacion.getTotal())
                .ost(cotizacion.getOst()) // retorna el objeto completo
                .build();
    }
    public static List<CotizacionResponse> fromEntities(List<Cotizacion> cotizaciones) {
        return cotizaciones.stream()
                .map(CotizacionResponse::fromEntity)
                .collect(Collectors.toList());
    }    
    
}
