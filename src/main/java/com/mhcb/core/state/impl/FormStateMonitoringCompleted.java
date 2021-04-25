package com.mhcb.core.state.impl;

import com.mhcb.core.state.FormState;
import com.mhcb.domain.State;

import java.util.ArrayList;
import java.util.List;

public class FormStateMonitoringCompleted implements FormState {
    private final String state = String.valueOf(State.MONITORING_COMPLETED);

    @Override
    public String getCurrentState() {
        return state;
    }

    @Override
    public List<FormState> getAvailableStates() {
        final List<FormState> list = new ArrayList<>();
        list.add(new FormStateMonitoringCompleted());
        return list;
    }
}
