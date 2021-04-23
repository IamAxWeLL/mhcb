package com.mhcb.domain;

import com.mhcb.core.state.FormState;
import com.mhcb.core.state.impl.*;
import com.mhcb.exception.FormNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class FormStateFactory {
    private final Map<String, FormState> map = new HashMap<>();

    public FormStateFactory() {
        map.put(State.NEW.name(), new FormStateNew());
        map.put(State.VETTING.name(), new FormStateVetting());
        map.put(State.REJECT_VETTING.name(), new FormStateRejectVetting());
        map.put(State.PHONE_INTERVIEW.name(), new FormStatePhoneInterview());
        map.put(State.REJECT_PHONE_INTERVIEW.name(), new FormStateRejectPhoneInterview());
        map.put(State.FIELD_VISIT.name(), new FormStateFieldVisit());
        map.put(State.REJECT_FIELD_VISIT.name(), new FormStateRejectFieldVisit());
        map.put(State.WAITING_FOR_APPROVAL.name(), new FormStateWaitingForApproval());
        map.put(State.APPROVED.name(), new FormStateApproved());
        map.put(State.REJECT_APPROVED.name(), new FormStateRejectApproved());
        map.put(State.DOCUMENTS.name(), new FormStateDocuments());
        map.put(State.WAITING_SIGNING.name(), new FormStateWaitingSigning());
        map.put(State.SIGNED.name(), new FormStateSigned());
        map.put(State.FUNDED.name(), new FormStateFunded());
        map.put(State.WAITING_RECEIPTS.name(), new FormStateWaitingReceipts());
        map.put(State.RECEIPTS_SUBMITTED.name(), new FormStateReceiptsSubmitted());
        map.put(State.REJECT_FOREVER.name(), new FormStateRejectForever());
        map.put(State.MONITORING.name(), new FormStateMonitoring());
        map.put(State.MONITORING_COMPLETED.name(), new FormStateMonitoringCompleted());
        map.put(State.PENDING.name(), new FormStatePending());
        map.put(State.ADMIN.name(), new FormStatesForAdmin());
    }

    public FormState getFormState(final String stateType) {
        if (isNull(stateType)) {
            throw new FormNotFoundException("parameter: " + stateType + " cannot be null");
        }
        return map.get(stateType);
    }
}
