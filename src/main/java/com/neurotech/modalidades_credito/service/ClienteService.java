package com.neurotech.modalidades_credito.service;

import org.springframework.stereotype.Service;

import com.neurotech.modalidades_credito.model.Cliente;

@Service
public class ClienteService {

    public boolean isCreditoComJurosFixos(Cliente cliente) {
        return cliente.getIdade() >= 18 && cliente.getIdade() <= 25;
    }

    public boolean isCreditoComJurosVariaveis(Cliente cliente) {
        return cliente.getIdade() >= 21 && cliente.getIdade() <= 65 && cliente.getRenda() >= 5000 && cliente.getRenda() <= 15000;
    }

    public boolean isCreditoConsignado(Cliente cliente) {
        return cliente.getIdade() > 65;
    }

    public boolean isAptoCreditoAutomotivoHatch(Cliente cliente) {
        return cliente.getRenda() >= 5000 && cliente.getRenda() <= 15000;
    }

    public boolean isAptoCreditoAutomotivoSUV(Cliente cliente) {
        return cliente.getRenda() > 8000 && cliente.getIdade() > 20;
    }
}
