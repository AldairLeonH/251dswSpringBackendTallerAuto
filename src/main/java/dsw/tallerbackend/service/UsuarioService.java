
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.UsuarioRequest;
import dsw.tallerbackend.dto.UsuarioResponse;
import dsw.tallerbackend.model.Persona;
import dsw.tallerbackend.model.Rol;
import dsw.tallerbackend.model.Usuario;
import dsw.tallerbackend.reporistory.PersonaRepository;
import dsw.tallerbackend.reporistory.RolRepository;
import dsw.tallerbackend.reporistory.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    PersonaRepository PersonaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder; 
    
    public List<UsuarioResponse> listUsuarios(){
        return UsuarioResponse.fromEntities(usuarioRepository.findAll());
        
    }
    public UsuarioResponse insertUsuario(UsuarioRequest usuarioRequest){
        Integer idRol = usuarioRequest.getIdRol();
        
        
        Rol rol = rolRepository.findById(idRol).get();
        if(rol==null) return new UsuarioResponse();
        
        Integer idPersona=usuarioRequest.getIdPersona();
        Persona persona=PersonaRepository.findById(idPersona).get();
        if(persona==null) return new UsuarioResponse();
        
        String passwordEncriptada = passwordEncoder.encode(usuarioRequest.getPassword());
        
        Usuario usuario = new Usuario(
                usuarioRequest.getIdUsuario(),
                usuarioRequest.getNombreUsuario(),
                passwordEncriptada,
                rol,
                persona
                 
        );
        usuario=usuarioRepository.save(usuario);
        return UsuarioResponse.fromEntity(usuario);
        
        
    }
    public UsuarioResponse updateUsuario(UsuarioRequest usuarioRequest){
        Integer idRol = usuarioRequest.getIdRol();
        Rol rol = rolRepository.findById(idRol).get();
        if(rol==null) return new UsuarioResponse();
        
        Integer idPersona=usuarioRequest.getIdPersona();
        Persona persona=PersonaRepository.findById(idPersona).get();
        if(persona==null) return new UsuarioResponse();
        
        Usuario usuario = new Usuario(
                usuarioRequest.getIdUsuario(),
                usuarioRequest.getNombreUsuario(),
                usuarioRequest.getPassword(),
                rol,
                persona
                 
        );
        usuario=usuarioRepository.save(usuario);
        return UsuarioResponse.fromEntity(usuario);
        
    }
    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
        
    }
    public UsuarioResponse findUsuario(Long id){
        Optional<Usuario> result=usuarioRepository.findById(id);
        if(!result.isPresent())
            return null;
        return UsuarioResponse.fromEntity(result.get());
        
        
    }    
    
}
