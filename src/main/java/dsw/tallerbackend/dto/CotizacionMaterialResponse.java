/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.dto;

import dsw.tallerbackend.model.Material;
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
public class CotizacionMaterialResponse {
    private Long id;
    private String nombre;
    private Integer stock;
    private Double precio;

    public static CotizacionMaterialResponse fromEntity(Material material) {
        return CotizacionMaterialResponse.builder()
                .id(material.getId())
                .nombre(material.getNombre())
                .stock(material.getStock())
                .precio(material.getPrecio())
                .build();
    }    
}
