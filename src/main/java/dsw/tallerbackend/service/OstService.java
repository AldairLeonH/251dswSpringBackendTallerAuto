/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.InventarioRevisionDTO;
import dsw.tallerbackend.dto.OstRequestDTO;
import dsw.tallerbackend.dto.OstResponseDTO;
import dsw.tallerbackend.model.Auto;
import dsw.tallerbackend.model.Direccion;
import dsw.tallerbackend.model.InventarioAuto;
import dsw.tallerbackend.model.Modelo;
import dsw.tallerbackend.model.OrdenPregunta;
import dsw.tallerbackend.model.OrdenPreguntaPK;
import dsw.tallerbackend.model.Ost;
import dsw.tallerbackend.model.Persona;
import dsw.tallerbackend.model.Pregunta;
import dsw.tallerbackend.model.TipoEstado;
import dsw.tallerbackend.model.Usuario;
import dsw.tallerbackend.reporistory.AutoRepository;
import dsw.tallerbackend.reporistory.DireccionRepository;
import dsw.tallerbackend.reporistory.InventarioAutoRepository;
import dsw.tallerbackend.reporistory.ItemInventarioRepository;
import dsw.tallerbackend.reporistory.ModeloRepository;
import dsw.tallerbackend.reporistory.OrdenPreguntaRepository;
import dsw.tallerbackend.reporistory.OstRepository;
import dsw.tallerbackend.reporistory.PersonaRepository;
import dsw.tallerbackend.reporistory.PreguntaRepository;
import dsw.tallerbackend.reporistory.TipoEstadoRepository;
import dsw.tallerbackend.reporistory.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ciro
 */
@Service
public class OstService {
    @Autowired private OstRepository ostRepository; 
    
    @Autowired private TipoEstadoRepository tipoEstadoRepository;
    
    @Autowired private DireccionRepository direccionRepository;
    
    @Autowired private PersonaRepository personaRepository;
    @Autowired
    private AutoRepository autoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PreguntaRepository preguntaRepository;
    
    @Autowired private OrdenPreguntaRepository ordenPreguntaRepo;
    
    @Autowired
    private ItemInventarioRepository itemInventarioRepository;
    
    @Autowired
    private InventarioAutoRepository inventarioAutoRepository;
    
    @Autowired private ModeloRepository modeloRepository;
    public List<OstResponseDTO> listOsts(){
        return OstResponseDTO.fromEntities(ostRepository.findAll());
    }
    
    public OstResponseDTO insertOst(OstRequestDTO ostRequestDTO) {
    // 1. Buscar tipoEstado de forma segura
    Optional<TipoEstado> tipoEstadoOpt = tipoEstadoRepository.findById(ostRequestDTO.getIdEstado());
    if (!tipoEstadoOpt.isPresent()) {
        return new OstResponseDTO(); // Estado inválido
    }
    TipoEstado tipoEstado = tipoEstadoOpt.get();
    
    Optional<Direccion> direccionOst = direccionRepository.findById(ostRequestDTO.getIdDireccion());
    if (!direccionOst.isPresent()) {
        return new OstResponseDTO(); // Direccion inválida
    }
    Direccion direccion = direccionOst.get();
    
    Auto auto;

    // 2. Si se envía el ID del auto, buscarlo
    if (ostRequestDTO.getIdAuto() != null) {
        auto = autoRepository.findById(ostRequestDTO.getIdAuto()).orElse(null);
        if (auto == null) {
            return new OstResponseDTO(); // Auto no encontrado
        }
    } else {
        // 3. Si no se envía ID, buscar por placa
        auto = autoRepository.findByPlaca(ostRequestDTO.getPlaca());

        if (auto != null) {
            throw new RuntimeException("Auto con placa " + ostRequestDTO.getPlaca() + " ya existe.");
        }

        // 4. Validar modelo
        Modelo modelo = modeloRepository.findByIdModelo(ostRequestDTO.getIdModelo()).orElse(null);
        if (modelo == null) {
            return new OstResponseDTO(); // Modelo no válido
        }

        // 5. Validar persona
        Persona persona = personaRepository.findById(ostRequestDTO.getIdPersona()).orElse(null);
        if (persona == null) {
            return new OstResponseDTO(); // Persona no válida
        }

        // 6. Crear y guardar nuevo auto
        auto = autoRepository.save(
            Auto.builder()
                .placa(ostRequestDTO.getPlaca())
                .anio(ostRequestDTO.getAnio())
                .color(ostRequestDTO.getColor())
                .modelo(modelo)
                .persona(persona)
                .build()
        );
    }

    // 7. Buscar usuario (recepcionista)
    Usuario usuario = usuarioRepository.findById(ostRequestDTO.getIdRecepcionista().longValue()).orElse(null);
    if (usuario == null) {
        return new OstResponseDTO(); // Usuario no válido
    }
    
    // 7. Buscar usuario (supervisor)
    Usuario usuario2 = usuarioRepository.findById(ostRequestDTO.getIdSupervisor().longValue()).orElse(null);
    if (usuario2 == null) {
        return new OstResponseDTO(); // Usuario no válido
    }

    // 8. Crear OST
    Ost ost = Ost.builder()
        .fecha(ostRequestDTO.getFecha())
        .fechaRevision(ostRequestDTO.getFechaRevision())
        .hora(ostRequestDTO.getHora())
        .nivelGasolina(ostRequestDTO.getNivelGasolina())
        .kilometraje(ostRequestDTO.getKilometraje())
        .direccion(direccion)
        .estado(tipoEstado)
        .auto(auto)
        .recepcionista(usuario)
        .supervisor(usuario2)
        .build();

    ost = ostRepository.save(ost);
        for (Integer idPregunta : ostRequestDTO.getPreguntas()) {
            Optional<Pregunta> preguntaOst = preguntaRepository.findById(idPregunta);
            if (!preguntaOst.isPresent()) {
                return new OstResponseDTO(); // Estado inválido
            }
            Pregunta pregunta = preguntaOst.get();

            ordenPreguntaRepo.save(
                OrdenPregunta.builder()
                    .id(new OrdenPreguntaPK(ost.getIdOst(), idPregunta))
                    .ost(ost)
                    .pregunta(pregunta)
                    .build()
            );
        }
        return OstResponseDTO.fromEntity(ost);
    }
    
    public OstResponseDTO updateOst(OstRequestDTO ostRequestDTO){
        Integer idTipoEstado = ostRequestDTO.getIdEstado();
        TipoEstado tipoEstado = tipoEstadoRepository.findById(idTipoEstado).get();
        if(tipoEstado==null) return new OstResponseDTO();
        
        Integer idDireccion = ostRequestDTO.getIdDireccion();
        Direccion direccion = direccionRepository.findById(idDireccion).get();
        if(direccion==null) return new OstResponseDTO();
        
        Integer idAuto = ostRequestDTO.getIdAuto();
        Auto auto = autoRepository.findById(idAuto).get();
        if(auto==null) return new OstResponseDTO();
        
        Integer idusuario = ostRequestDTO.getIdRecepcionista();
        Usuario usuario = usuarioRepository.findById(idusuario.longValue()).orElse(null);
        if(usuario==null) return new OstResponseDTO();
        Ost ost = Ost.builder()
            .fecha(ostRequestDTO.getFecha())
            .hora(ostRequestDTO.getHora())
            .direccion(direccion)
            .estado(tipoEstado)
            .auto(auto)
            .recepcionista(usuario)
            .build();
        ost=ostRepository.save(ost);
        return OstResponseDTO.fromEntity(ost);
    }
    
    @Transactional
    public void deleteOst(int id) {
        if (!ostRepository.existsById(id)) {
            throw new RuntimeException("Ost no encontrado");
        }
        
        // Eliminar las relaciones en orden_pregunta
        ordenPreguntaRepo.deleteByIdIdOst(id);

        // Luego eliminar la OST
        ostRepository.deleteById(id);
    }
    
    public OstResponseDTO findOst(Integer id){
        Optional<Ost> result=ostRepository.findById(id);
        if(!result.isPresent())
            return null;
        return OstResponseDTO.fromEntity(result.get());
    }

    public List<OstResponseDTO> obtenerOstPorCliente(Integer idUsuario) {
        Usuario usuario = (Usuario) usuarioRepository.findById(idUsuario);
        Integer idPersona = usuario.getPersona().getIdPersona(); // asegúrate que no sea null
        List<Ost> osts = ostRepository.findByIdPersonaCliente(idPersona);

        return osts.stream()
                   .map(OstResponseDTO::fromEntity)
                   .collect(Collectors.toList());
    }
    
    public List<OstResponseDTO> obtenerOstPorSupervisor(Long idSupervisor) {
        List<Ost> osts = ostRepository.findBySupervisorId(idSupervisor);
        return osts.stream()
                   .map(OstResponseDTO::fromEntity)
                   .collect(Collectors.toList());
    }
    
    public void actualizarInventarioYRevision(Integer idOst, InventarioRevisionDTO dto) {
    Ost ost = ostRepository.findById(idOst)
        .orElseThrow(() -> new RuntimeException("OST no encontrada"));

    ost.setKilometraje(dto.getKilometraje());
        System.out.println(dto.getKilometraje());
        System.out.println(dto.getNivelGasolina());
    ost.setNivelGasolina(dto.getNivelGasolina());

    ostRepository.save(ost);

    // Elimina inventario anterior si lo deseas

    List<InventarioAuto> inventarios = dto.getInventario().stream().map(itemDTO -> {
        InventarioAuto inv = new InventarioAuto();
        inv.setOst(ost);
        //inv.setIdItem(itemInventarioRepository.findById(itemDTO.getIdItem()).orElseThrow(() -> new RuntimeException("Item no encontrado")));
        inv.setCantidad(itemDTO.getCantidad());
        inv.setEstado(itemDTO.getEstado());
        return inv;
    }).collect(Collectors.toList());

    inventarioAutoRepository.saveAll(inventarios);
}
    
}
