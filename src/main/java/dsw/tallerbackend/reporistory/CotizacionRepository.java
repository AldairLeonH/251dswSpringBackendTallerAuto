/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsw.tallerbackend.reporistory;

import dsw.tallerbackend.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ciro
 */
@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion,Long>{
    
}
