package com.mhcb.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
    public FormState convertToEntityAttribute(String state) {
        if (state == null) {
            return null;
        }
        return FormStateMicro.of(state);
    }
}
