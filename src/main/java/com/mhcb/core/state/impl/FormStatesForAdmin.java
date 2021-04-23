package com.mhcb.core.state.impl;

import com.mhcb.core.state.FormState;
import com.mhcb.domain.State;
import com.mhcb.domain.UserRole;

import java.util.ArrayList;
import java.util.List;

public class FormStatesForAdmin implements FormState {
    private final String state = String.valueOf(State.ADMIN);

    @Override
    public String getCurrentState() {
        return state;
    }

    @Override
    public List<FormState> getAvailableStates(final UserRole userRole) {
        final List<FormState> list = new ArrayList<>();
        list.add(new FormStatesForAdmin());
        list.add(new FormStateRejectApproved());
        list.add(new FormStateDocuments());
        list.add(new FormStateFieldVisit());
        list.add(new FormStateFunded());
        list.add(new FormStateMonitoring());
        list.add(new FormStateMonitoringCompleted());
        list.add(new FormStateNew());
        list.add(new FormStatePending());
        list.add(new FormStatePhoneInterview());
        list.add(new FormStateReceiptsSubmitted());
        list.add(new FormStateRejectApproved());
        list.add(new FormStateRejectFieldVisit());
        list.add(new FormStateRejectForever());
        list.add(new FormStateRejectPhoneInterview());
        list.add(new FormStateRejectVetting());
        list.add(new FormStateSigned());
        list.add(new FormStateVetting());
        list.add(new FormStateWaitingForApproval());
        list.add(new FormStateWaitingReceipts());
        list.add(new FormStateWaitingSigning());
        return list;
    }
}
