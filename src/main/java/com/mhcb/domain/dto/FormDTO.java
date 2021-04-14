package com.mhcb.domain.dto;

import com.mhcb.domain.FormState;
import lombok.Data;

import java.util.List;

@Data
public class FormDTO {

    private Long id;
    private FormState state;
    private List<FormState> availableStates;
}
