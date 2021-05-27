package com.mhcb.domain;

import static java.util.Objects.isNull;

public class FormStateMicroFactory {

    public FormStateMicro getFormStateMicro(final String stateType) {
        if (isNull(stateType)) {
            throw new IllegalArgumentException("State type cannot be null.");
        }
        return FormStateMicro.of(stateType);
    }
}
