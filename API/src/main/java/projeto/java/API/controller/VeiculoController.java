package projeto.java.API.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.java.API.entity.Veiculo;
import projeto.java.API.model.VeiculoRequestDTO;
import projeto.java.API.service.VeiculoService;

import java.util.List;

@RestController
@RequestMapping ("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Valid VeiculoRequestDTO dto){
        Veiculo veiculo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar(){
        return ResponseEntity.ok(service.listarVeiculos());
    }
}
