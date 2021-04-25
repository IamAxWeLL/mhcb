package com.mhcb.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class FormDTO {
    private Long id;
    private String currentState;
    private List<String> availableStates;
}
