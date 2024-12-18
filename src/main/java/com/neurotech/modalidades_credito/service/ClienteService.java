package com.neurotech.modalidades_credito.service;

import org.springframework.stereotype.Service;

import com.neurotech.modalidades_credito.model.Cliente;

@Service
public class ClienteService {

    public boolean isCreditoComJurosFixos(Cliente cliente) {
        return cliente.getAge() >= 18 && cliente.getAge() <= 25;
    }

    public boolean isCreditoComJurosVariaveis(Cliente cliente) {
        return cliente.getAge() >= 21 && cliente.getAge() <= 65 && cliente.getIncome() >= 5000 && cliente.getIncome() <= 15000;
    }

    public boolean isCreditoConsignado(Cliente cliente) {
        return cliente.getAge() > 65;
    }

    public boolean isAptoCreditoAutomotivoHatch(Cliente cliente) {
        return cliente.getIncome() >= 5000 && cliente.getIncome() <= 15000;
    }

    public boolean isAptoCreditoAutomotivoSUV(Cliente cliente) {
        return cliente.getIncome() > 8000 && cliente.getAge() > 20;
    }
}
