package dsw.tallerbackend.controller;

import dsw.tallerbackend.dto.OstTecnicoCompletoDTO;
import dsw.tallerbackend.dto.OstTecnicoRequestDTO;
import dsw.tallerbackend.dto.OstTecnicoResponseDTO;
import dsw.tallerbackend.service.OstTecnicoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ost-tecnico")
public class OstTecnicoController {

    @Autowired private OstTecnicoService service;

    @PostMapping("/asignar")
    public ResponseEntity<?> asignarTecnico(@RequestBody OstTecnicoRequestDTO dto) {
        System.out.println("fafafafa");
        service.asignarMultiplesTecnicos(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/por-ost/{idOst}")
    public ResponseEntity<List<OstTecnicoResponseDTO>> obtenerPorOst(@PathVariable Integer idOst) {
        return ResponseEntity.ok(service.obtenerAsignacionesPorOst(idOst));
    }

    @DeleteMapping("/{idOst}/{idTecnico}")
    public ResponseEntity<?> eliminarAsignacion(@PathVariable Integer idOst, @PathVariable Long idTecnico) {
        service.eliminarAsignacion(idOst, idTecnico);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/finalizar/{idOst}/{idTecnico}")
    public ResponseEntity<?> finalizarTrabajo(
        @PathVariable Integer idOst,
        @PathVariable Long idTecnico,
        @RequestBody Map<String, String> body
    ) {
        String observaciones = body.getOrDefault("observaciones", "");
        service.finalizarTrabajo(idOst, idTecnico, observaciones);
        return ResponseEntity.ok("Trabajo finalizado");
    }
    
    @GetMapping("/por-tecnico/{idTecnico}")
    public ResponseEntity<List<OstTecnicoCompletoDTO>> obtenerPorTecnico(@PathVariable Long idTecnico) {
        return ResponseEntity.ok(service.obtenerOstsPorTecnico(idTecnico));
    }
}
