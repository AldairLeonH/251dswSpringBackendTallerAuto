/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.OstRequestDTO;
import dsw.tallerbackend.dto.OstResponseDTO;
import dsw.tallerbackend.model.Auto;
import dsw.tallerbackend.model.Ost;
import dsw.tallerbackend.model.TipoEstado;
import dsw.tallerbackend.model.Usuario;
import dsw.tallerbackend.reporistory.AutoRepository;
import dsw.tallerbackend.reporistory.OstRepository;
import dsw.tallerbackend.reporistory.TipoEstadoRepository;
import dsw.tallerbackend.reporistory.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ciro
 */
@Service
public class OstService {
    @Autowired
    OstRepository ostRepository; 
    
    @Autowired
    private TipoEstadoRepository tipoEstadoRepository;
    
    @Autowired
    private AutoRepository autoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<OstResponseDTO> listOsts(){
        return OstResponseDTO.fromEntities(ostRepository.findAll());
    }
    public OstResponseDTO insertOst(OstRequestDTO ostRequestDTO){
        
        /*TipoEstado tipoEstado = null;
        if (ostRequestDTO.getIdEstado() != null) {
            tipoEstado = tipoEstadoRepository.findById(ostRequestDTO.getIdEstado())
                .orElse(null);
        }
        Auto auto = null;
        if (ostRequestDTO.getIdAuto() != null) {
            auto = autoRepository.findById(ostRequestDTO.getIdAuto())
                .orElse(null);
        }
        Usuario usuario = null;
        if (ostRequestDTO.getIdRecepcionista() != null) {
            usuario = usuarioRepository.findById(ostRequestDTO.getIdRecepcionista().longValue())
                .orElse(null);
        }*/   
        Integer idTipoEstado = ostRequestDTO.getIdEstado();
        TipoEstado tipoEstado = tipoEstadoRepository.findById(idTipoEstado).get();
        if(tipoEstado==null) return new OstResponseDTO();

        Integer idAuto = ostRequestDTO.getIdAuto();
        Auto auto = autoRepository.findById(idAuto).get();
        if(auto==null) return new OstResponseDTO();
        
        Integer idusuario = ostRequestDTO.getIdRecepcionista();
        Usuario usuario = usuarioRepository.findById(idusuario.longValue()).orElse(null);
        if(usuario==null) return new OstResponseDTO();
        Ost ost = new Ost(
                ostRequestDTO.getIdOst(),
                ostRequestDTO.getFecha(),
                ostRequestDTO.getHora(),
                ostRequestDTO.getDireccion(),
                tipoEstado,
                auto,
                usuario
        );
        ost=ostRepository.save(ost);
        return OstResponseDTO.fromEntity(ost);
    }
    public OstResponseDTO updateOst(OstRequestDTO ostRequestDTO){
        Integer idTipoEstado = ostRequestDTO.getIdEstado();
        TipoEstado tipoEstado = tipoEstadoRepository.findById(idTipoEstado).get();
        if(tipoEstado==null) return new OstResponseDTO();

        Integer idAuto = ostRequestDTO.getIdAuto();
        Auto auto = autoRepository.findById(idAuto).get();
        if(auto==null) return new OstResponseDTO();
        
        Integer idusuario = ostRequestDTO.getIdRecepcionista();
        Usuario usuario = usuarioRepository.findById(idusuario.longValue()).orElse(null);
        if(usuario==null) return new OstResponseDTO();
        Ost ost = new Ost(
                ostRequestDTO.getIdOst(),
                ostRequestDTO.getFecha(),
                ostRequestDTO.getHora(),
                ostRequestDTO.getDireccion(),
                tipoEstado,
                auto,
                usuario
        );
        ost=ostRepository.save(ost);
        return OstResponseDTO.fromEntity(ost);
    }
    public void deleteOst(int id) {
        if (!ostRepository.existsById(id)) {
            throw new RuntimeException("Ost no encontrado");
        }
        ostRepository.deleteById(id);
    }
    public OstResponseDTO findOst(Integer id){
        Optional<Ost> result=ostRepository.findById(id);
        if(!result.isPresent())
            return null;
        return OstResponseDTO.fromEntity(result.get());
        
        
    }
    
    
    
    
    
    
    
    
    
    
}
