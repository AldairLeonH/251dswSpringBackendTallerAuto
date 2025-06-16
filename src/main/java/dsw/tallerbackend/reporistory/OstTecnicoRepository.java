/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.reporistory;

import dsw.tallerbackend.model.OstTecnico;
import dsw.tallerbackend.model.OstTecnicoId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OstTecnicoRepository extends JpaRepository<OstTecnico, OstTecnicoId> {
    Optional<OstTecnico> findById_IdOstAndId_IdTecnico(Integer idOst, Long idTecnico);

    List<OstTecnico> findById_IdOst(Integer idOst);

    List<OstTecnico> findById_IdTecnico(Long idTecnico);
}
