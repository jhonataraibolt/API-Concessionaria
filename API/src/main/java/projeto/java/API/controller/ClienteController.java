package projeto.java.API.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.java.API.entity.Cliente;
import projeto.java.API.model.ClienteRequestDTO;
import projeto.java.API.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping ("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid ClienteRequestDTO dto){
        Cliente cliente = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        return ResponseEntity.ok(service.ListarTodos());
    }
}
