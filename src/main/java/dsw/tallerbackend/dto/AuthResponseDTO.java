package dsw.tallerbackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {

        private String token;
        private UsuarioLoginDTO usuario;
        /* Escalable---
        private String username;
        private List<String> roles;*/
}
