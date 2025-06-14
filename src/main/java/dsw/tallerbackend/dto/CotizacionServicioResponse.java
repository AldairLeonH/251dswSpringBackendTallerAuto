/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.dto;

import dsw.tallerbackend.model.Servicio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CotizacionServicioResponse {
    private Long idServicio;
    private String nombre;
    private Double precio;

    public static CotizacionServicioResponse fromEntity(Servicio servicio) {
        return CotizacionServicioResponse.builder()
                .idServicio(servicio.getId())
                .nombre(servicio.getNombre())
                .precio(servicio.getPrecio())
                .build();
    }    
}
