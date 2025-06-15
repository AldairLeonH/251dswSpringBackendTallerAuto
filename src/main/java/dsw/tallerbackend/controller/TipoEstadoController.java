
package dsw.tallerbackend.controller;

import dsw.tallerbackend.dto.TipoEstadoDTO;
import dsw.tallerbackend.service.TipoEstadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aldair
 */

@RestController
@RequestMapping("/api/v1/tipo-estado")
public class TipoEstadoController {
    
    @Autowired
    private TipoEstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<TipoEstadoDTO>> listar() {
        return ResponseEntity.ok(estadoService.listarTodos());
    }
}
