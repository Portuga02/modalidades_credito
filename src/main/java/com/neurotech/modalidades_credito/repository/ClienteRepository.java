package com.neurotech.modalidades_credito.repository;

import com.neurotech.modalidades_credito.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ClienteRepository {

    private final Map<String, Cliente> clientes = new HashMap<>();

    public Cliente save(String id, Cliente cliente) {
        return clientes.put(id, cliente);
    }

    public Cliente findById(String id) {
        return clientes.get(id);
    }

    public Map<String, Cliente> findAll() {
        return clientes;
    }
}
