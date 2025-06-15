
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.TipoEstadoDTO;
import dsw.tallerbackend.reporistory.TipoEstadoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aldair
 */
@Service
public class TipoEstadoService {

    @Autowired private TipoEstadoRepository estadoRepository;

    public List<TipoEstadoDTO> listarTodos() {
        return estadoRepository.findAll().stream()
                .map(estado -> TipoEstadoDTO.builder()
                        .id(estado.getIdEstado())
                        .estado(estado.getEstado())
                        .build())
                .collect(Collectors.toList());
    }
}