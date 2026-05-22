package projeto.java.API.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.java.API.entity.Cliente;
import projeto.java.API.entity.Veiculo;
import projeto.java.API.exception.DuplicateResourceException;
import projeto.java.API.exception.ArgumentNotValidException;
import projeto.java.API.exception.ResourceNotFoundException;
import projeto.java.API.model.VeiculoRequestDTO;
import projeto.java.API.model.VeiculoResponseDTO;
import projeto.java.API.model.VeiculoUpdateDTO;
import projeto.java.API.repository.VeiculoRepository;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;
    @Autowired
    private ClienteService service;

    public VeiculoResponseDTO salvar(VeiculoRequestDTO dto){
        if (repository.existsByPlaca(dto.placa())) {
            throw new DuplicateResourceException("Placa já cadastrada");
        }

        if(Boolean.TRUE.equals(dto.vendido()) && dto.valorVenda() == null){
            throw new ArgumentNotValidException("O valor da venda é obrigatório para os veiculos vendidos");
        }

        Cliente proprietario = service.buscarPorId(dto.clienteId());
        Veiculo veiculo = new Veiculo();
        BeanUtils.copyProperties(dto,veiculo); //usei o BeansUtils para copiar de um para outro
        veiculo.setProprietario(proprietario);
        repository.save(veiculo);

        return new VeiculoResponseDTO(veiculo);
    }

    public List<Veiculo> listarVeiculos(){
        return repository.findAll();
    }

    public VeiculoResponseDTO atualizar(UUID id, VeiculoUpdateDTO dto){
        Veiculo veiculoExistente = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Veiculo com id " + id + " não encontrado!"));

        if(Boolean.TRUE.equals(dto.vendido() )&& dto.valorVenda() == null){
            throw new ArgumentNotValidException("O valor da venda é obrigatório para os veiculos vendidos");
        }

        if (dto.marca() != null && !dto.marca().isEmpty()) veiculoExistente.setMarca(dto.marca());
        if (dto.modelo() != null && !dto.modelo().isEmpty()) veiculoExistente.setModelo(dto.modelo());
        if (dto.ano() != null ) veiculoExistente.setAno(dto.ano());
        if (dto.valor() != null) veiculoExistente.setValor(dto.valor());
        if (dto.maximoDesconto() != null) veiculoExistente.setMaximoDesconto(dto.maximoDesconto());
        if (dto.vendido() != null) veiculoExistente.setVendido(dto.vendido());
        if (dto.valorVenda() != null) veiculoExistente.setValorVenda(dto.valorVenda());

        repository.save(veiculoExistente);
        return new VeiculoResponseDTO(veiculoExistente);
    }

    public void deletar (UUID id){
        Veiculo veiculo = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Veiculo com id " + id + " não encontrado!"));
        repository.delete(veiculo);
    }

    public Veiculo buscarPorPlaca(String placa){
        return repository.findByPlaca(placa).orElseThrow(()-> new ResourceNotFoundException("Veiculo com a placa " + placa + " não encontrada!"));
    }

    public List<Veiculo> buscarPorMarca (String marca){
        return repository.findByMarcaContainingIgnoreCase(marca);
    }

    public List<Veiculo> buscarPorModelo (String modelo){
        return repository.findByModeloContainingIgnoreCase(modelo);
    }

    public List<VeiculoResponseDTO> buscarComFiltros(String placa,String marca,String modelo){ //get buscando por placa, marca e modelo

        List<Veiculo> veiculos;

        if (placa != null && !placa.isEmpty()) {
            veiculos = List.of(buscarPorPlaca(placa));
        }else if (marca != null && !marca.isEmpty()) {
           veiculos = buscarPorMarca(marca);
        }else if (modelo != null && !modelo.isEmpty()) {
            veiculos = buscarPorModelo(modelo);
        }else {
            veiculos = listarVeiculos();
        }
        return veiculos.stream().map(VeiculoResponseDTO::new).toList();
    }
}
