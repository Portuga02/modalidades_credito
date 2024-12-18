package com.neurotech.modalidades_credito.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.neurotech.modalidades_credito.model.Cliente;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        cliente = new Cliente("Bob", 40, 10000);
    }

    @Test
    public void cadastrarCliente() throws Exception {
        mockMvc.perform(post("/api/modalidades-credito")
                .contentType("application/json")
                .content("{\"name\":\"Bob\",\"age\":40,\"income\":10000}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/modalidades-credito/1"));
    }

    @Test
    public void obterCliente() throws Exception {
        mockMvc.perform(get("/api/modalidades-credito/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"));
    }

    @Test
    public void verificarCreditoHatch() throws Exception {
        mockMvc.perform(post("/api/modalidades-credito/1/credito-hatch"))
                .andExpect(status().isOk())
                .andExpect(content().string("Apto para crédito automotivo - Hatch"));
    }
}
