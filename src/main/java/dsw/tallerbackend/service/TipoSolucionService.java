/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.service;

import dsw.tallerbackend.model.TipoSolucion;
import dsw.tallerbackend.reporistory.TipoSolucionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ciro
 */
@Service
public class TipoSolucionService {
    @Autowired
    TipoSolucionRepository tipoSolucionRepository ;
    public List<TipoSolucion> getTipoSoluciones(){
        return tipoSolucionRepository.findAll();
    }
    
}
