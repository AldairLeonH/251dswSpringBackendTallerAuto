/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.controller;

import dsw.tallerbackend.dto.InventarioRevisionDTO;
import dsw.tallerbackend.dto.OstRequestDTO;
import dsw.tallerbackend.dto.OstResponseDTO;
import dsw.tallerbackend.service.OstService;
import dsw.tallerbackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ciro
 */
@RestController
@RequestMapping(path = "api/v1/ost")
public class OstController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OstService ostService;
    
    @GetMapping
    public ResponseEntity<?> getOsts(){
        List<OstResponseDTO> listaOstResponseDTO=null;
        try{
            listaOstResponseDTO=ostService.listOsts();
        
        }catch(Exception e){
            logger.error("error inesperado",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaOstResponseDTO.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("ost not found").build());
        return ResponseEntity.ok(listaOstResponseDTO);
        
    }
    @GetMapping("/mis-ost/{idUsuario}")
    public ResponseEntity<?> obtenerMisOst(@PathVariable Integer idUsuario) {
        List<OstResponseDTO> lista = ostService.obtenerOstPorCliente(idUsuario);
        return ResponseEntity.ok(lista);
    }
    @PostMapping
    public ResponseEntity<?> insertOst(@RequestBody OstRequestDTO ostRequestDTO){
        logger.info(">insert"+ostRequestDTO.toString());
        OstResponseDTO ostResponseDTO;
        try{
            ostResponseDTO=ostService.insertOst(ostRequestDTO);
            
        }catch(Exception e){
            
            logger.error("error inesperado",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);     
        }        
        if(ostResponseDTO==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("ost not insert").build());
        }
        return ResponseEntity.ok(ostResponseDTO); 
        
    }
    @PutMapping()
    public ResponseEntity<?> updateOst(@RequestBody OstRequestDTO ostRequestDTO){ 
        logger.info(">update" + ostRequestDTO.toString());
        OstResponseDTO ostResponseDTO;
        try{
            ostResponseDTO=ostService.findOst(ostRequestDTO.getIdOst());
            if(ostResponseDTO==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("ost not found").build());
            ostResponseDTO=ostService.updateOst(ostRequestDTO);
        }catch(Exception e){
            
            logger.error("error inesperado",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);     
        }        
        if(ostResponseDTO==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("ost not update").build());
        return ResponseEntity.ok(ostResponseDTO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOst(@PathVariable int id) {
        ostService.deleteOst(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/actualizar-inventario/{idOst}")
    public ResponseEntity<?> actualizarInventario(@PathVariable Integer idOst,
            @RequestBody InventarioRevisionDTO dto) {
        ostService.actualizarInventarioYRevision(idOst, dto);
        return ResponseEntity.ok("Inventario y datos de revisión actualizados.");
    }
}
