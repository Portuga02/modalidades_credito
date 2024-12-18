package com.neurotech.modalidades_credito.controller;

import java.net.URI;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neurotech.modalidades_credito.model.Cliente;
import com.neurotech.modalidades_credito.repository.ClienteRepository;
import com.neurotech.modalidades_credito.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController

@RequestMapping("/api")
public class ClienteController {
    String LOCATION = "http://localhost:8080/api/client/";
    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteService clienteService, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/client")
    @ApiOperation(value = "Cadastrar cliente", response = Cliente.class)
    public ResponseEntity<Cliente> cadastrarCliente(@Validated @RequestBody Cliente cliente) {
        String id = String.valueOf(clienteRepository.findAll().size() + 1);
        HttpHeaders responseHeaders = new HttpHeaders();
            
        clienteRepository.save(id, cliente);
        responseHeaders.set("Location",LOCATION + id  );
        return ResponseEntity.ok().headers(responseHeaders).body(cliente);
          
    }

    @GetMapping("/client/{id}")
    @ApiOperation(value = "Retornar dados do cliente", response = Cliente.class)
    public ResponseEntity<Cliente> obterCliente(@PathVariable String id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/client/{id}/credito-hatch")
    @ApiOperation(value = "Verificar elegibilidade para crédito automotivo - Hatch")
    public ResponseEntity<String> verificarCreditoHatch(@PathVariable String id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        boolean apto = clienteService.isAptoCreditoAutomotivoHatch(cliente);
        return apto ? ResponseEntity.ok("Apto para crédito automotivo - Hatch")
                : ResponseEntity.status(HttpStatus.FORBIDDEN).body("Não apto para crédito automotivo - Hatch");
    }

    @PostMapping("/client/{id}/credito-suv")
    @ApiOperation(value = "Verificar elegibilidade para crédito automotivo - SUV")
    public ResponseEntity<String> verificarCreditoSUV(@PathVariable String id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        boolean apto = clienteService.isAptoCreditoAutomotivoSUV(cliente);
        return apto ? ResponseEntity.ok("Apto para crédito automotivo - SUV")
                : ResponseEntity.status(HttpStatus.FORBIDDEN).body("Não apto para crédito automotivo - SUV");
    }

    @GetMapping("/credito-fixo-hatch")
    @ApiOperation(value = "Clientes aptos a crédito fixo e crédito Hatch")
    public ResponseEntity<List<String>> clientesCreditoFixoHatch() {
        List<String> clientesAptos = clienteRepository.findAll().values().stream()
                .filter(cliente -> clienteService.isCreditoComJurosFixos(cliente)
                && clienteService.isAptoCreditoAutomotivoHatch(cliente)
                && cliente.getIdade() >= 23 && cliente.getIdade() <= 49)
                .map(cliente -> "Nome: " + cliente.getNome() + ", Renda: " + cliente.getRenda())
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientesAptos);
    }
}
