package com.javafarmacy.activeprinciple.application;

import com.javafarmacy.activeprinciple.domain.entity.Activeprinciple;
import com.javafarmacy.activeprinciple.domain.service.ActiveprincipleService;

public class UpdateActiveprincipleUseCase {
    private final ActiveprincipleService activeprincipleService;

    public UpdateActiveprincipleUseCase(ActiveprincipleService activeprincipleService) {
        this.activeprincipleService = activeprincipleService;
    }

    public void execute(Activeprinciple activeprinciple){
        activeprincipleService.updateActiveprinciple(activeprinciple);
    }
}
