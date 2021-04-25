package com.mhcb.controller;

import com.mhcb.domain.UserRole;
import com.mhcb.domain.dto.StateDTO;
import com.mhcb.service.StateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/states")
public class StateController {

    private final StateService stateService;

    public StateController(final StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public StateDTO getAllStates(final UserRole userRole, final String currentState) {

        return stateService.getAllStates(userRole, currentState);
    }
}
