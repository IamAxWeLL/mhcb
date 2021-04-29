package com.mhcb.domain;

import com.mhcb.core.state.FormState;
import com.mhcb.exception.FormNotFoundException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class FormConverter implements AttributeConverter<FormState, String> {

    @Override
    public String convertToDatabaseColumn(final FormState state) {
        if (Objects.isNull(state)) {
            throw new FormNotFoundException("parameter: " + state + " cannot be null");
        }
            return state.getCurrentState();
    }

    @Override
    public FormState convertToEntityAttribute(final String state) {
        if (state == null) {
            return null;
        }
        return new FormStateFactory().getFormStateForString(state);
    }
}
