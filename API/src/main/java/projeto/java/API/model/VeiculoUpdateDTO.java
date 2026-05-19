package projeto.java.API.model;

public record VeiculoUpdateDTO (
        String marca,
        String modelo,
        Integer ano,
        Double valor,
        Double maximoDesconto,
        Boolean vendido,
        Double valorVenda
){}
