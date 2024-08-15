package com.javafarmacy.activeprinciple.application;

import com.javafarmacy.activeprinciple.domain.entity.Activeprinciple;
import com.javafarmacy.activeprinciple.domain.service.ActiveprincipleService;

public class DeleteActiveprincipleUseCase {
  private final ActiveprincipleService activeprincipleService;

    public  DeleteActiveprincipleUseCase(ActiveprincipleService activeprincipleService) {
        this.activeprincipleService = activeprincipleService;
    }

    public Activeprinciple execute(String codeActiveprinciple){
        return activeprincipleService.deleteActiveprinciple(codeActiveprinciple);
    }
}
