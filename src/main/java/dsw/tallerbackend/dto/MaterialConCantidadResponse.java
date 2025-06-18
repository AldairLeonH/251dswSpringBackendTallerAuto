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
public class MaterialConCantidadResponse {
    private Long idMaterial;
    private String nombre;
    private Integer stock;
    private Double precio;
    private Integer cantidad;

    public static MaterialConCantidadResponse fromEntity(Material material, int cantidad) {
        return MaterialConCantidadResponse.builder()
                .idMaterial(material.getId())
                .nombre(material.getNombre())
                .stock(material.getStock())
                .precio(material.getPrecio())
                .cantidad(cantidad)
                .build();
    }    
    
}
