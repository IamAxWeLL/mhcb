package com.mhcb.domain;

public enum State {
    NEW, VETTING, REJECT_VETTING, PHONE_INTERVIEW, REJECT_PHONE_INTERVIEW, FIELD_VISIT, REJECT_FIELD_VISIT,
    WAITING_FOR_APPROVAL, APPROVED, REJECT_APPROVED, DOCUMENTS, WAITING_SIGNING, SIGNED, FUNDED,
    WAITING_RECEIPTS, RECEIPTS_SUBMITTED, REJECT_FOREVER, MONITORING, MONITORING_COMPLETED, PENDING, ADMIN;

    private String stateType;

    State() {
    }

    private State(String stateType) {
        this.stateType = stateType;
    }

    public String getStateType() {
        return stateType;
    }
}
