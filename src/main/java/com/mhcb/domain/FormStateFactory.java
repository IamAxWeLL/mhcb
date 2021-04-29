package com.mhcb.domain;

import com.mhcb.core.state.FormState;
import com.mhcb.core.state.impl.*;
import com.mhcb.exception.FormNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class FormStateFactory {

    private final Map<String, FormState> mapString = new HashMap<>();
    private final Map<State, FormState> mapState = new HashMap<>();

    public FormStateFactory() {
        mapString.put(State.NEW.name(), new FormStateNew());
        mapString.put(State.VETTING.name(), new FormStateVetting());
        mapString.put(State.REJECT_VETTING.name(), new FormStateRejectVetting());
        mapString.put(State.PHONE_INTERVIEW.name(), new FormStatePhoneInterview());
        mapString.put(State.REJECT_PHONE_INTERVIEW.name(), new FormStateRejectPhoneInterview());
        mapString.put(State.FIELD_VISIT.name(), new FormStateFieldVisit());
        mapString.put(State.REJECT_FIELD_VISIT.name(), new FormStateRejectFieldVisit());
        mapString.put(State.WAITING_FOR_APPROVAL.name(), new FormStateWaitingForApproval());
        mapString.put(State.APPROVED.name(), new FormStateApproved());
        mapString.put(State.REJECT_APPROVED.name(), new FormStateRejectApproved());
        mapString.put(State.DOCUMENTS.name(), new FormStateDocuments());
        mapString.put(State.WAITING_SIGNING.name(), new FormStateWaitingSigning());
        mapString.put(State.SIGNED.name(), new FormStateSigned());
        mapString.put(State.FUNDED.name(), new FormStateFunded());
        mapString.put(State.WAITING_RECEIPTS.name(), new FormStateWaitingReceipts());
        mapString.put(State.RECEIPTS_SUBMITTED.name(), new FormStateReceiptsSubmitted());
        mapString.put(State.REJECT_FOREVER.name(), new FormStateRejectForever());
        mapString.put(State.MONITORING.name(), new FormStateMonitoring());
        mapString.put(State.MONITORING_COMPLETED.name(), new FormStateMonitoringCompleted());
        mapString.put(State.PENDING.name(), new FormStatePending());
        mapState.put(State.NEW, new FormStateNew());
        mapState.put(State.VETTING, new FormStateVetting());
        mapState.put(State.REJECT_VETTING, new FormStateRejectVetting());
        mapState.put(State.PHONE_INTERVIEW, new FormStatePhoneInterview());
        mapState.put(State.REJECT_PHONE_INTERVIEW, new FormStateRejectPhoneInterview());
        mapState.put(State.FIELD_VISIT, new FormStateFieldVisit());
        mapState.put(State.REJECT_FIELD_VISIT, new FormStateRejectFieldVisit());
        mapState.put(State.WAITING_FOR_APPROVAL, new FormStateWaitingForApproval());
        mapState.put(State.APPROVED, new FormStateApproved());
        mapState.put(State.REJECT_APPROVED, new FormStateRejectApproved());
        mapState.put(State.DOCUMENTS, new FormStateDocuments());
        mapState.put(State.WAITING_SIGNING, new FormStateWaitingSigning());
        mapState.put(State.SIGNED, new FormStateSigned());
        mapState.put(State.FUNDED, new FormStateFunded());
        mapState.put(State.WAITING_RECEIPTS, new FormStateWaitingReceipts());
        mapState.put(State.RECEIPTS_SUBMITTED, new FormStateReceiptsSubmitted());
        mapState.put(State.REJECT_FOREVER, new FormStateRejectForever());
        mapState.put(State.MONITORING, new FormStateMonitoring());
        mapState.put(State.MONITORING_COMPLETED, new FormStateMonitoringCompleted());
        mapState.put(State.PENDING, new FormStatePending());
    }

    public FormState getFormStateForState(final State stateType) {
        if (isNull(stateType)) {
            throw new FormNotFoundException("parameter: " + stateType + " cannot be null");
        }
        return mapState.get(stateType);
    }

    public FormState getFormStateForString(final String stateType) {
        if (isNull(stateType)) {
            throw new FormNotFoundException("parameter: " + stateType + " cannot be null");
        }
        return mapString.get(stateType);
    }
}
