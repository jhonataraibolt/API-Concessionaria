package projeto.java.API.model;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.bridge.IMessage;

import java.util.UUID;

public record VeiculoRequestDTO(

        @NotBlank(message = "Marca obrigatório")
        String marca,

        @NotBlank(message = "Modelo obrigatório")
        String modelo,

        @NotBlank(message = "Placa obrigatório")
        String placa,

        @NotNull (message = "Ano obrigatório")
        Integer ano,

        @NotNull (message = "Id do cliente obrogatório")
        UUID clienteId
){}
