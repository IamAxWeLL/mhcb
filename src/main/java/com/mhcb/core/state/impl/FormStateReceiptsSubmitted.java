package com.mhcb.core.state.impl;

import com.mhcb.core.state.FormState;
import com.mhcb.domain.State;
import com.mhcb.domain.UserRole;

import java.util.ArrayList;
import java.util.List;

public class FormStateReceiptsSubmitted implements FormState {
    private final String state = String.valueOf(State.RECEIPTS_SUBMITTED);

    @Override
    public String getCurrentState() {
        return state;
    }

    @Override
    public List<FormState> getAvailableStates(final UserRole userRole) {
        final List<FormState> list = new ArrayList<>();
        list.add(new FormStateReceiptsSubmitted());
        list.add(new FormStateMonitoring());
        return list;
    }
}
