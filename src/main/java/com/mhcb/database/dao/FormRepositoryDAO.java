package com.mhcb.database.dao;

import com.mhcb.database.repository.FormRepository;
import com.mhcb.domain.Form;
import com.mhcb.core.state.FormState;
import com.mhcb.domain.FormStateFactory;
import com.mhcb.domain.dto.FormDTO;
import com.mhcb.exception.FormNotFoundException;
import org.springframework.stereotype.Repository;

import static com.mhcb.domain.UserRole.USER;

@Repository
public class FormRepositoryDAO {

    private final FormRepository formRepository;

    public FormRepositoryDAO(final FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public Form add(final String state) {
        final Form newForm = new Form();
        final FormStateFactory factory = new FormStateFactory();
        newForm.setFormState(factory.getFormState(state));
        return formRepository.save(newForm);
    }

    public FormDTO getAvailableFormStates(final Long id) {
        final Form form = formRepository
                .findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form with id: " + id + " not found"));
        final FormDTO formDTO = new FormDTO();
        formDTO.setId(form.getId());
        final FormState formState = form.getFormState();
        formDTO.setCurrentState(formState);
        formDTO.setAvailableStates(formState.getAvailableStates(USER));
        return formDTO;
    }
}
