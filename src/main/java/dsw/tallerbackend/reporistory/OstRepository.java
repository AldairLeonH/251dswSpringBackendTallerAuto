/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsw.tallerbackend.reporistory;

import dsw.tallerbackend.model.Ost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ciro
 */
@Repository
public interface OstRepository extends JpaRepository<Ost, Integer> {
    public List<Ost> findBySupervisorId(Long idSupervisor);
    public List<Ost> findByRecepcionistaId(Long idCliente);
    @Query("SELECT o FROM Ost o WHERE o.auto.persona.id = :idPersona")
    public List<Ost> findByIdPersonaCliente(Integer idPersona);
}
