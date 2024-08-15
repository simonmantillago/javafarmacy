package com.javafarmacy.activeprinciple.application;

import java.util.Optional;

import com.javafarmacy.activeprinciple.domain.entity.Activeprinciple;
import com.javafarmacy.activeprinciple.domain.service.ActiveprincipleService;

public class FindActiveprincipleByIdUseCase {
    private final ActiveprincipleService activeprincipleService;

    public  FindActiveprincipleByIdUseCase(ActiveprincipleService activeprincipleService) {
        this.activeprincipleService = activeprincipleService;
    }

    public Optional<Activeprinciple> execute(String codeActiveprinciple){
        return activeprincipleService.findActiveprincipleById(codeActiveprinciple);
    }
}
