package projeto.java.API.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
   @NotBlank String nome,
   @NotBlank String telefone,
   @NotBlank String cpf,
   @NotBlank @Email String email
) {}
