
package dsw.tallerbackend.dto;

import dsw.tallerbackend.model.InventarioAuto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventarioAutoResponseDTO {
    private Long idInventario;
    private String descripcionItem;
    private String categoria;
    private Integer cantidad;
    private String estado;
    
    
    public static InventarioAutoResponseDTO fromEntity(InventarioAuto entity) {
        return InventarioAutoResponseDTO.builder()
            .idInventario(entity.getIdInventario())
            .descripcionItem(entity.getIdItem().getNombre())
            .categoria(entity.getIdItem().getCategoria().getNombre())
            .cantidad(entity.getCantidad())
            .estado(entity.getEstado())
            .build();
    }
}