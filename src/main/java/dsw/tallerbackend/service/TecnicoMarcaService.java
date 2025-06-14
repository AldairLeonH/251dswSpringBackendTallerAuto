
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.TecnicoMarcaRequestDTO;
import dsw.tallerbackend.model.Marca;
import dsw.tallerbackend.model.Tecnico;
import dsw.tallerbackend.model.TecnicoMarca;
import dsw.tallerbackend.model.TecnicoMarcaId;
import dsw.tallerbackend.reporistory.MarcaRepository;
import dsw.tallerbackend.reporistory.TecnicoMarcaRepository;
import dsw.tallerbackend.reporistory.TecnicoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoMarcaService {

    @Autowired private TecnicoRepository tecnicoRepository;
    @Autowired private MarcaRepository marcaRepository;
    @Autowired private TecnicoMarcaRepository tecnicoMarcaRepository;

    public void asignarMarcaATecnico(TecnicoMarcaRequestDTO dto) {
        TecnicoMarcaId id = TecnicoMarcaId.builder()
        .id(dto.getIdTecnico())
        .idMarca(dto.getIdMarca())
        .build();

        if (tecnicoMarcaRepository.existsById(id)) {
            throw new RuntimeException("La relación ya existe.");
        }
        
        Tecnico tecnico = tecnicoRepository.findById(dto.getIdTecnico())
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));

        Marca marca = marcaRepository.findById(dto.getIdMarca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));

        TecnicoMarca tecnicoMarca = TecnicoMarca.builder()
                .id(id)
                .tecnico(tecnico)
                .marca(marca)
                .build();

        tecnicoMarcaRepository.save(tecnicoMarca);
    }

    public List<String> obtenerMarcasPorTecnico(Long idTecnico) {
        return tecnicoMarcaRepository.findByTecnico_Id(idTecnico)
                .stream()
                .map(t -> t.getMarca().getNombre())
                .collect(Collectors.toList());
    }

    public List<Long> obtenerTecnicosPorMarca(Integer idMarca) {
        return tecnicoMarcaRepository.findByMarca_IdMarca(idMarca)
                .stream()
                .map(t -> t.getTecnico().getId())
                .collect(Collectors.toList());
    }
    public void eliminarRelacion(TecnicoMarcaRequestDTO dto) {
        TecnicoMarcaId id = TecnicoMarcaId.builder()
                .id(dto.getIdTecnico())
                .idMarca(dto.getIdMarca())
                .build();

        if (!tecnicoMarcaRepository.existsById(id)) {
            throw new RuntimeException("La relación no existe.");
        }

        tecnicoMarcaRepository.deleteById(id);
    }
}
