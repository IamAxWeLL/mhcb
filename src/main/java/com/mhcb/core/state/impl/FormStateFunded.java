package com.mhcb.core.state.impl;

import com.mhcb.core.state.FormState;
import com.mhcb.domain.State;
import com.mhcb.domain.UserRole;

import java.util.ArrayList;
import java.util.List;

public class FormStateFunded implements FormState {
    private final String state = String.valueOf(State.FUNDED);

    @Override
    public String getCurrentState() {
        return state;
    }

    @Override
    public List<FormState> getAvailableStates(final UserRole userRole) {
        final List<FormState> list = new ArrayList<>();
        list.add(new FormStateFunded());
        list.add(new FormStateWaitingReceipts());
        return list;
    }
}
