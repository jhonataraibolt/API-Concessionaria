package projeto.java.API.model;

import projeto.java.API.entity.Veiculo;

import java.util.UUID;

public record VeiculoResponseDTO (
        UUID id,
        UUID clienteId,
        String marca,
        String modelo,
        Integer ano,
        Double valor,
        String placa,
        Double maximoDesconto,
        Boolean vendido,
        Double valorVenda
) {
    public VeiculoResponseDTO(Veiculo veiculo) {
        this(
                veiculo.getID(),
                veiculo.getProprietario().getId(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getAno(),
                veiculo.getValor(),
                veiculo.getPlaca(),
                veiculo.getMaximoDesconto(),
                veiculo.getVendido(),
                veiculo.getValorVenda()
        );
    }
}
