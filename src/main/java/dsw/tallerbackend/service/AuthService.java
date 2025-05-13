package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.AuthResponseDTO;
import dsw.tallerbackend.dto.LoginRequestDTO;
import dsw.tallerbackend.dto.RegisterRequestDTO;
import dsw.tallerbackend.model.Persona;
import dsw.tallerbackend.model.Rol;
import dsw.tallerbackend.model.TipoDocumento;
import dsw.tallerbackend.model.Usuario;
import dsw.tallerbackend.reporistory.PersonaRepository;
import dsw.tallerbackend.reporistory.RolRepository;
import dsw.tallerbackend.reporistory.TipoDocumentoRepository;
import dsw.tallerbackend.reporistory.UsuarioRepository;
import dsw.tallerbackend.utils.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtService = null;
    
    public AuthResponseDTO register(RegisterRequestDTO request) {
        // checking...
        if (usuarioRepository.existsByNombreUsuario(request.getNombreUsuario())) {
            throw new IllegalArgumentException("Hay un usuario registrado con ese email");
        }
        if (personaRepository.existsByNumeroDocumento(request.getNroDocumento())) {
            throw new IllegalArgumentException("Hay un usuario registrado con ese nro de documento");
        }
        //char sexo=request.getSexo().charAt(0);
        TipoDocumento tipoDoc = tipoDocumentoRepository.findByNombre(request.getTipoDocumento())
            .orElseThrow(() -> new RuntimeException("Tipo de documento no válido"));

        Persona persona = Persona.builder()
            .nroDocumento(request.getNroDocumento())
            .apellidoPaterno(request.getApellidoPaterno())
            .apellidoMaterno(request.getApellidoMaterno())
            .nombres(request.getNombres())
            .direccion(request.getDireccion())
            .sexo(request.getSexo())
            .telefono(request.getTelefono())
            .tipoDocumento(tipoDoc)
            .build();

        personaRepository.save(persona);

        Rol rol = rolRepository.findByNombre("Cliente") // O el que venga del DTO
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = Usuario.builder()
            .nombreUsuario(request.getNombreUsuario())
            .password(passwordEncoder.encode(request.getPassword()))
            .persona(persona)
            .rol(rol)
            .build();

        usuarioRepository.save(usuario);


        String token = jwtService.generateToken(usuario);
        return AuthResponseDTO.builder()
            .token(token)
            .build();
    }
    
    public AuthResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(request.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("Nombre de usuario no encontrado"));
            
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Contrasena incorrecta");
        }
        
        String token = jwtService.generateToken(usuario);
        return AuthResponseDTO.builder()
            .token(token)
            .build();
    }
}
