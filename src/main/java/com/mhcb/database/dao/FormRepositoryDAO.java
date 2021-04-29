package com.mhcb.database.dao;

import com.mhcb.database.repository.FormRepository;
import com.mhcb.domain.*;
import com.mhcb.domain.dto.FormDTO;
import com.mhcb.exception.FormNotFoundException;
import com.mhcb.service.StateService;
import org.springframework.stereotype.Repository;

@Repository
public class FormRepositoryDAO {

    private final FormRepository formRepository;
    private final StateService stateService;

    public FormRepositoryDAO(final FormRepository formRepository, StateService stateService) {
        this.formRepository = formRepository;
        this.stateService = stateService;
    }

    public Form add(final State state) {
        final Form newForm = new Form();
        final FormStateFactory2 factory = new FormStateFactory2();
        newForm.setFormState(factory.getFormState(state));
        return formRepository.save(newForm);
    }

    public FormDTO getAvailableFormStates(final Long id, final UserRole userRole) {
        final Form form = formRepository
                .findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form with id: " + id + " not found"));

        final FormDTO formDTO = new FormDTO();
        formDTO.setId(form.getId());
        final State currentState = State.valueOf(form.getFormState().getCurrentState());
        formDTO.setCurrentState(currentState.name());
        formDTO.setAvailableStates(stateService.getAllStates(userRole, currentState).getAvailableStates());
        return formDTO;
    }
}
