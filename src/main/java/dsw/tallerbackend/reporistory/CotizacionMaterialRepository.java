/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsw.tallerbackend.reporistory;

/**
 *
 * @author Ciro
 */
import dsw.tallerbackend.model.CotizacionMaterial;
import dsw.tallerbackend.model.CotizacionMaterialId;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionMaterialRepository extends JpaRepository<CotizacionMaterial, CotizacionMaterialId> {
    List<CotizacionMaterial> findByCotizacionId(Long cotizacionId);

    
}
