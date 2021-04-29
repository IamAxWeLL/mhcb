package com.mhcb.domain;

import com.mhcb.core.state.FormState;
import com.mhcb.core.state.impl.*;
import com.mhcb.exception.FormNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class FormStateFactory2 {
    private final Map<State, FormState> map = new HashMap<>();

    public FormStateFactory2() {
        map.put(State.NEW, new FormStateNew());
        map.put(State.VETTING, new FormStateVetting());
        map.put(State.REJECT_VETTING, new FormStateRejectVetting());
        map.put(State.PHONE_INTERVIEW, new FormStatePhoneInterview());
        map.put(State.REJECT_PHONE_INTERVIEW, new FormStateRejectPhoneInterview());
        map.put(State.FIELD_VISIT, new FormStateFieldVisit());
        map.put(State.REJECT_FIELD_VISIT, new FormStateRejectFieldVisit());
        map.put(State.WAITING_FOR_APPROVAL, new FormStateWaitingForApproval());
        map.put(State.APPROVED, new FormStateApproved());
        map.put(State.REJECT_APPROVED, new FormStateRejectApproved());
        map.put(State.DOCUMENTS, new FormStateDocuments());
        map.put(State.WAITING_SIGNING, new FormStateWaitingSigning());
        map.put(State.SIGNED, new FormStateSigned());
        map.put(State.FUNDED, new FormStateFunded());
        map.put(State.WAITING_RECEIPTS, new FormStateWaitingReceipts());
        map.put(State.RECEIPTS_SUBMITTED, new FormStateReceiptsSubmitted());
        map.put(State.REJECT_FOREVER, new FormStateRejectForever());
        map.put(State.MONITORING, new FormStateMonitoring());
        map.put(State.MONITORING_COMPLETED, new FormStateMonitoringCompleted());
        map.put(State.PENDING, new FormStatePending());
    }

    public FormState getFormState(final State stateType) {
        if (isNull(stateType)) {
            throw new FormNotFoundException("parameter: " + stateType + " cannot be null");
        }
        return map.get(stateType);
    }
}
