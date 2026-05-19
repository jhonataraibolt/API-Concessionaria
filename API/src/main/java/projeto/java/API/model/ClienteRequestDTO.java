package projeto.java.API.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
   @NotBlank(message = "Nome obrigatório")
   String nome,

   @NotBlank(message = "Telefone obrigatório")
   @Size(min = 11,max = 11)
   String telefone,

   @NotBlank(message = "CPF obrigatório")
   @Size(min = 11, max = 11)
   String cpf,

   @NotBlank(message = "Email obrigatório")
   @Email(message = "Email invalido")
   String email
) {}
