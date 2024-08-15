package com.javafarmacy.activeprinciple.domain.service;

import java.util.Optional;

import com.javafarmacy.activeprinciple.domain.entity.Activeprinciple;

public interface ActiveprincipleService {
    void createActiveprinciple (Activeprinciple activeprinciple);
    void updateActiveprinciple (Activeprinciple activeprinciple);
    Activeprinciple deleteActiveprinciple (String codeActiveprinciple);
    Optional<Activeprinciple> findActiveprincipleById(String codeActiveprinciple);
}
