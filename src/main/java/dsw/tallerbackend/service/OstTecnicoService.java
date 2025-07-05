package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.AsignacionTecnicoDTO;
import dsw.tallerbackend.dto.OstResponseDTO;
import dsw.tallerbackend.dto.OstTecnicoCompletoDTO;
import dsw.tallerbackend.dto.OstTecnicoRequestDTO;
import dsw.tallerbackend.dto.OstTecnicoResponseDTO;
import dsw.tallerbackend.model.Ost;
import dsw.tallerbackend.model.OstTecnico;
import dsw.tallerbackend.model.OstTecnicoId;
import dsw.tallerbackend.model.Tecnico;
import dsw.tallerbackend.model.TipoEstado;
import dsw.tallerbackend.reporistory.OstRepository;
import dsw.tallerbackend.reporistory.OstTecnicoRepository;
import dsw.tallerbackend.reporistory.TecnicoRepository;
import dsw.tallerbackend.reporistory.TipoEstadoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OstTecnicoService {

    @Autowired private OstTecnicoRepository ostTecnicoRepository;
    @Autowired private TipoEstadoRepository estadoRepository;
    @Autowired private TecnicoRepository tecnicoRepository;
    @Autowired private OstRepository ostRepository;

    public void asignarMultiplesTecnicos(OstTecnicoRequestDTO dto) {
    Ost ost = ostRepository.findById(dto.getIdOst())
        .orElseThrow(() -> new RuntimeException("OST no encontrada"));
    for (AsignacionTecnicoDTO asignacion : dto.getAsignaciones()) {
        Tecnico tecnico = tecnicoRepository.findById(asignacion.getIdTecnico())
            .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));

        TipoEstado estado = estadoRepository.findById(asignacion.getIdEstado())
            .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        
        OstTecnico ostTecnico = OstTecnico.builder()
            .id(new OstTecnicoId(dto.getIdOst(), tecnico.getId()))
            .estado(estado)
            .fechaAsignacion(LocalDateTime.now())
            .observaciones(asignacion.getObservaciones())
            .build();
        
        ostTecnicoRepository.save(ostTecnico);
    }
}
    
    // Marcar como finalizada una asignación
    public void finalizarTrabajo(Integer idOst, Long idTecnico, String observaciones) {
        OstTecnico relacion = ostTecnicoRepository.findById_IdOstAndId_IdTecnico(idOst, idTecnico)
            .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        relacion.setFechaFinalizacion(LocalDateTime.now());
        relacion.setObservaciones(observaciones);

        ostTecnicoRepository.save(relacion);
    }
    
        // Eliminar asignación de técnico
    public void eliminarAsignacion(Integer idOst, Long idTecnico) {
        OstTecnico relacion = ostTecnicoRepository.findById_IdOstAndId_IdTecnico(idOst, idTecnico)
            .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
        
        ostTecnicoRepository.delete(relacion);
    }
    
    public List<OstTecnicoResponseDTO> obtenerAsignacionesPorOst(Integer idOst) {
        return ostTecnicoRepository.findById_IdOst(idOst).stream().map(rel -> OstTecnicoResponseDTO.builder()
                .idOst(rel.getId().getIdOst())
                .idTecnico(rel.getId().getIdTecnico())
                .estado(rel.getEstado().getEstado())
                .fechaAsignacion(rel.getFechaAsignacion())
                .fechaFinalizacion(rel.getFechaFinalizacion())
                .observaciones(rel.getObservaciones())
                .build()).collect(Collectors.toList());
    }
    

    // Obtener todas las OST asignadas a un técnico
    public List<OstTecnicoCompletoDTO> obtenerOstsPorTecnico(Long idTecnico) {
        return ostTecnicoRepository.findById_IdTecnico(idTecnico).stream()
            .map(rel -> {
                Ost ost = rel.getOrdenServicio(); // o rel.getOst() si tienes la relación con fetch
                OstResponseDTO ostDto = OstResponseDTO.fromEntity(ost); // tu método de mapeo
                return 
                OstTecnicoCompletoDTO.builder()
                .ost(ostDto)
                .estadoAsignacion(rel.getEstado().getEstado())
                .fechaAsignacion(rel.getFechaAsignacion())
                .fechaFinalizacion(rel.getFechaFinalizacion())
                .observaciones(rel.getObservaciones())
                .build();
            }).collect(Collectors.toList());
    }

}