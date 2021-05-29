package com.mhcb.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class StateDTO {
    private List<String> availableStates;

    private StateDTO(final List<String> availableStates) {
        this.availableStates = availableStates;
    }

    public static StateDTO of(final List<String> availableStates) {
        return new StateDTO(availableStates);
    }
}
