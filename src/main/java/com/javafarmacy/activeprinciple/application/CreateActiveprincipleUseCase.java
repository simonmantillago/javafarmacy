package com.javafarmacy.activeprinciple.application;

import com.javafarmacy.activeprinciple.domain.entity.Activeprinciple;
import com.javafarmacy.activeprinciple.domain.service.ActiveprincipleService;

public class CreateActiveprincipleUseCase {
    private final ActiveprincipleService activeprincipleService;

    public CreateActiveprincipleUseCase(ActiveprincipleService activeprincipleService) {
        this.activeprincipleService = activeprincipleService;
    }

    public void execute(Activeprinciple activeprinciple){
        activeprincipleService.createActiveprinciple(activeprinciple);
    }
}
