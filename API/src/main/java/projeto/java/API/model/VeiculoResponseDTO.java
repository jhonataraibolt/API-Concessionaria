package projeto.java.API.model;

import projeto.java.API.entity.Veiculo;

import java.util.UUID;

public record VeiculoResponseDTO (
        UUID id,
        String marca,
        String modelo,
        String placa,
        Integer ano,
        Double valor,
        Double maximoDesconto,
        Boolean vendido,
        Double valorVenda,
        UUID clienteId,
        String nomeProprietario
) {
    public VeiculoResponseDTO(Veiculo veiculo) {
        this(
                veiculo.getID(),
        veiculo.getMarca(),
        veiculo.getModelo(),
        veiculo.getPlaca(),
        veiculo.getAno(),
        veiculo.getValor(),
        veiculo.getMaximoDesconto(),
        veiculo.getVendido(),
        veiculo.getValorVenda(),
        veiculo.getProprietario().getId(),
        veiculo.getProprietario().getNome()
        );
    }
}
