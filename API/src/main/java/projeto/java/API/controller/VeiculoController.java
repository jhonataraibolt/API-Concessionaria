package projeto.java.API.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.java.API.entity.Veiculo;
import projeto.java.API.model.VeiculoRequestDTO;
import projeto.java.API.model.VeiculoResponseDTO;
import projeto.java.API.model.VeiculoUpdateDTO;
import projeto.java.API.service.VeiculoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> cadastrar(@RequestBody @Valid VeiculoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<VeiculoResponseDTO> atualizar(@PathVariable UUID id, @RequestBody @Valid VeiculoUpdateDTO dto ){
        return ResponseEntity.ok(service.atualizar(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable UUID id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> buscar(@RequestParam(required = false) String placa, @RequestParam(required = false) String marca, @RequestParam(required = false) String modelo){
        return ResponseEntity.ok(service.buscarComFiltros(placa,marca,modelo));
    }

}
