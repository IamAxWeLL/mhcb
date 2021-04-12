package com.mhcb.domain;

public class FormStateMicroFactory {

    public FormStateMicro getFormStateMicro(String stateType) {
        if (stateType == null) {
            return null;
        }
        if (stateType.equalsIgnoreCase("NEW")) {
            return FormStateMicro.of(State.NEW.getStateType());
        }  else {
            return null;
        }
    }
}
