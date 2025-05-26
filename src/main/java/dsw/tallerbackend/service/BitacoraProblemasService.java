/**
 *
 * @author Ciro
 */
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.BitacoraProblemasRequestDTO;
import dsw.tallerbackend.dto.BitacoraProblemasResponseDTO;
import dsw.tallerbackend.model.BitacoraProblemas;
import dsw.tallerbackend.model.TipoSolucion;
import dsw.tallerbackend.reporistory.BitacoraProblemasRepository;
import dsw.tallerbackend.reporistory.TipoSolucionRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitacoraProblemasService {

    @Autowired
    private BitacoraProblemasRepository bitacoraProblemasRepository;//

    @Autowired
    private TipoSolucionRepository tipoSolucionRepository;//

    public List<BitacoraProblemasResponseDTO> listarBitacoras() {
        return BitacoraProblemasResponseDTO.fromEntities(bitacoraProblemasRepository.findAll());
    }

    public BitacoraProblemasResponseDTO insertarBitacora(BitacoraProblemasRequestDTO request) {
        Optional<TipoSolucion> tipoSolucionOpt = tipoSolucionRepository.findById(request.getIdTipoSolucion());
        if (!tipoSolucionOpt.isPresent()) {
            throw new RuntimeException("TipoSolucion no encontrado");
        }

        BitacoraProblemas bitacora = BitacoraProblemas.builder()
                .idProblema(request.getIdProblema())
                .descripcionProblema(request.getDescripcionProblema())
                .solucion(request.getSolucion())
                .tipoSolucion(tipoSolucionOpt.get())
                .fechaRegistro(request.getFechaRegistro())
                .build();

        bitacora = bitacoraProblemasRepository.save(bitacora);
        return BitacoraProblemasResponseDTO.fromEntity(bitacora);
    }

    public BitacoraProblemasResponseDTO actualizarBitacora(BitacoraProblemasRequestDTO request) {
        if (!bitacoraProblemasRepository.existsById(request.getIdProblema())) {
            throw new RuntimeException("Bitacora no encontrada");
        }

        Optional<TipoSolucion> tipoSolucionOpt = tipoSolucionRepository.findById(request.getIdTipoSolucion());
        if (!tipoSolucionOpt.isPresent()) {
            throw new RuntimeException("TipoSolucion no encontrado");
        }

        BitacoraProblemas bitacora = BitacoraProblemas.builder()
                .idProblema(request.getIdProblema())
                .descripcionProblema(request.getDescripcionProblema())
                .solucion(request.getSolucion())
                .tipoSolucion(tipoSolucionOpt.get())
                .fechaRegistro(request.getFechaRegistro())
                .build();

        bitacora = bitacoraProblemasRepository.save(bitacora);
        return BitacoraProblemasResponseDTO.fromEntity(bitacora);
    }

    public void eliminarBitacora(Integer id) {
        if (!bitacoraProblemasRepository.existsById(id)) {
            throw new RuntimeException("Bitácora no encontrada");
        }
        bitacoraProblemasRepository.deleteById(id);
    }

    public BitacoraProblemasResponseDTO buscarBitacora(Integer id) {
        Optional<BitacoraProblemas> bitacoraOpt = bitacoraProblemasRepository.findById(id);
        return bitacoraOpt.map(BitacoraProblemasResponseDTO::fromEntity).orElse(null);
    }
}

