package com.mhcb.core.state;

import com.mhcb.domain.UserRole;

import java.util.List;

public interface FormState {
    String getCurrentState();

    List<FormState> getAvailableStates(final UserRole userRole);
}
