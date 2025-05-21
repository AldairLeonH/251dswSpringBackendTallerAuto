/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsw.tallerbackend.reporistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dsw.tallerbackend.model.Auto;
import java.util.List;



@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer> {

    public Auto findByPlaca(String placa);
    List<Auto> findByPersona_IdPersona(Integer idPersona);
}
