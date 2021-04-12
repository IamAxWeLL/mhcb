package com.mhcb.domain;

import lombok.Data;

@Data
public class FormStateMicro implements FormState{

    private String currentState;

    public FormStateMicro() {
    }

    public static FormStateMicro of(final String state) {
        final FormStateMicro formStateMicro = new FormStateMicro();
        formStateMicro.setCurrentState(state);
        return formStateMicro;
    }

    @Override
    public String getCurrentState() {
        return currentState;
    }
}
