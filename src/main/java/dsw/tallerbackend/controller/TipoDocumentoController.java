
package dsw.tallerbackend.controller;

import dsw.tallerbackend.model.TipoDocumento;
import dsw.tallerbackend.service.TipoDocumentoService;
import dsw.tallerbackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/tipoDocumento")
public class TipoDocumentoController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    TipoDocumentoService tipoDocumentoService;
    
    @GetMapping
    public ResponseEntity<?> getTipoDocumentos(){
        List<TipoDocumento> listaTipoDocumento=null;
        try{
            listaTipoDocumento=tipoDocumentoService.getTipoDocumentos();
        
        }catch(Exception e){
            logger.error("error inesperado",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaTipoDocumento.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("tipoDocumento not found").build());
        return ResponseEntity.ok(listaTipoDocumento);
    }    

}