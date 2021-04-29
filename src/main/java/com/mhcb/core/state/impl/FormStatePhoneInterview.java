package com.mhcb.core.state.impl;

import com.mhcb.core.state.FormState;
import com.mhcb.domain.State;
import com.mhcb.domain.UserRole;

import java.util.ArrayList;
import java.util.List;

public class FormStatePhoneInterview implements FormState {
    private final String state = String.valueOf(State.PHONE_INTERVIEW);

    @Override
    public String getCurrentState() {
        return state;
    }

    @Override
    public List<FormState> getAvailableStates() {
        final List<FormState> list = new ArrayList<>();
        list.add(new FormStatePhoneInterview());
        list.add(new FormStateRejectPhoneInterview());
        list.add(new FormStateFieldVisit());
        return list;
    }
}
