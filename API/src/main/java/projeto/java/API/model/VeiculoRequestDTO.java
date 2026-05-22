package projeto.java.API.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record VeiculoRequestDTO(

        @NotBlank(message = "Marca obrigatório")
        String marca,

        @NotBlank(message = "Modelo obrigatório")
        String modelo,

        @NotBlank(message = "Placa obrigatório")
        @Size(min = 7,max = 7, message = "Placa é obrigatório e deve conter 7 caracteres")
        String placa,

        @NotNull (message = "Ano obrigatório")
        @Min(1900)
        Integer ano,

        @NotNull(message = "Valor da veiculo é obrigatório")
        @Min(1)
        Double valor,

        @NotNull (message = "Id do cliente obrogatório")
        UUID clienteId,

        @NotNull (message = "Desconto máximo é Obrigatório")
        @Min(0)
        Double maximoDesconto,

        @NotNull(message = "Status é obrigatório")
        Boolean vendido,

        Double valorVenda
){}
