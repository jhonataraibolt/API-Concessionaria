package projeto.java.API.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.java.API.entity.Cliente;
import projeto.java.API.model.ClienteRequestDTO;
import projeto.java.API.model.ClienteResponseDTO;
import projeto.java.API.service.ClienteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody @Valid ClienteRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable UUID id){
        service.Deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping //get de nome e cpf
    public ResponseEntity<List<ClienteResponseDTO>> buscarCliente(@RequestParam(required = false)String nome,@RequestParam(required = false) String cpf){
        return ResponseEntity.ok(service.buscarComFiltros(nome, cpf));
    }

}
