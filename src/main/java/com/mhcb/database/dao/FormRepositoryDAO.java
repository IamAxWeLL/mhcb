package com.mhcb.database.dao;

import com.mhcb.database.repository.FormRepository;
import com.mhcb.domain.*;
import com.mhcb.domain.dto.FormDTO;
import com.mhcb.domain.dto.ProfileDTO;
import com.mhcb.exception.FormNotFoundException;
import com.mhcb.service.StateService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class FormRepositoryDAO {

    private final FormRepository formRepository;
    private final StateService stateService;

    public FormRepositoryDAO(final FormRepository formRepository, StateService stateService) {
        this.formRepository = formRepository;
        this.stateService = stateService;
    }

    public void addForm(final FormDTO formDTO) {
        final FormStateFactory formStateFactory = new FormStateFactory();
        final Form formToDb = new Form();
        formToDb.setFormState(formStateFactory.getFormStateForString(formDTO.getCurrentState()));
        formToDb.setPerson(formDTO.getPerson());
        formToDb.setAddress(formDTO.getAddress());
        formRepository.save(formToDb);
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

    public ProfileDTO getProfileByFormId(final Long id) {
        final Form form = formRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile with id:" + id + " is missing"));
        final ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFullName(form.getPerson().getFullName());
        profileDTO.setFullAddress(form.getAddress().getFullStreetName());
        return profileDTO;
    }
}
