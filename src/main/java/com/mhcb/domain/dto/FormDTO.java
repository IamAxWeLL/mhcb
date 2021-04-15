package com.mhcb.domain.dto;

import com.mhcb.core.state.FormState;
import lombok.Data;

import java.util.List;

@Data
public class FormDTO {

    private Long id;
    private FormState currentState;
    private List<FormState> availableStates;
}
