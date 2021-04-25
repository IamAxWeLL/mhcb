package com.mhcb.core.state.impl;

import com.mhcb.core.state.FormState;
import com.mhcb.domain.State;

import java.util.ArrayList;
import java.util.List;

public class FormStateNew implements FormState {
    private final String state = String.valueOf(State.NEW);

    @Override
    public String getCurrentState() {
        return state;
    }

    @Override
    public List<FormState> getAvailableStates() {
        final List<FormState> list = new ArrayList<>();
        list.add(new FormStateNew());
        list.add(new FormStateVetting());
        return list;
    }
}
