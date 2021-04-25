package com.mhcb.service;

import com.mhcb.core.state.FormState;
import com.mhcb.core.state.impl.*;
import com.mhcb.domain.FormStateFactory;
import com.mhcb.domain.UserRole;
import com.mhcb.domain.dto.StateDTO;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mhcb.domain.UserRole.ADMIN;
import static java.util.stream.Collectors.toList;

@Service
@Data
public class StateService {
    public StateDTO getAllStates(final UserRole userRole, final String currentState) {
        final List<FormState> list = new ArrayList<>();
        if(userRole.equals(ADMIN)){
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
        }
        else {
            final FormStateFactory factory = new FormStateFactory();
            final FormState formState = factory.getFormState(currentState);
            final List<FormState> availableStates = formState.getAvailableStates();
            list.addAll(availableStates);
        }

        return convertFormStateToString(list);
    }
    private StateDTO convertFormStateToString(final List<FormState> stateList){
        final List<String> collectedList = stateList.stream().map(FormState::getCurrentState)
                .collect(toList());
        final StateDTO stateDTO = new StateDTO();
        stateDTO.setAvailableStates(collectedList);
        return stateDTO;
    }
}
