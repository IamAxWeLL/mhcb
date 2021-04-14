package com.mhcb.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.isNull;

@Converter(autoApply = true)
public class FormConverter implements AttributeConverter<FormState, String> {

    @Override
    public String convertToDatabaseColumn(FormState state) {
        if (state == null) {
            return "";
        }
            return state.getCurrentState();
    }

    @Override
    public FormState convertToEntityAttribute(final String state) {
        if (isNull(state)) {
            return null;
        }
        return FormStateMicro.of(state);
    }
}
