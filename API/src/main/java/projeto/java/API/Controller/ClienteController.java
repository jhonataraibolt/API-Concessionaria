package projeto.java.API.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.java.API.Entity.Cliente;
import projeto.java.API.Model.ClienteRequestDTO;
import projeto.java.API.Service.ClienteService;

import java.util.List;

@RestController
@RequestMapping ("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid ClienteRequestDTO dto){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto,cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        return ResponseEntity.ok(service.ListarTodos());
    }
}
