package com.mhcb.core.state;

import java.util.List;

public interface FormState {
    String getCurrentState();

    List<FormState> getAvailableStates();
}
