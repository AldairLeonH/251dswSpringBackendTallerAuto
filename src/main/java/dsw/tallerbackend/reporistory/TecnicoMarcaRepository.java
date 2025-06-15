/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.reporistory;

import dsw.tallerbackend.model.TecnicoMarca;
import dsw.tallerbackend.model.TecnicoMarcaId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoMarcaRepository extends JpaRepository<TecnicoMarca, TecnicoMarcaId> {

    List<TecnicoMarca> findByTecnico_Id(Long idTecnico);

    List<TecnicoMarca> findByMarca_IdMarca(Integer idMarca);
}
