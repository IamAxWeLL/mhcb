package com.mhcb.database.dao;

import com.mhcb.core.state.FormState;
import com.mhcb.database.repository.FormRepository;
import com.mhcb.domain.Form;
import com.mhcb.domain.FormStateFactory;
import com.mhcb.domain.UserRole;
import com.mhcb.domain.dto.FormDTO;
import com.mhcb.exception.FormNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

import static com.mhcb.domain.UserRole.*;

@Repository
public class FormRepositoryDAO {

    private final FormRepository formRepository;

    public FormRepositoryDAO(final FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public Form add(final String state, final String userRole) {
        final Form newForm = new Form();
        final FormStateFactory factory = new FormStateFactory();
        newForm.setFormState(factory.getFormState(state));
        final UserRole newUserRole = valueOf(userRole.toUpperCase());
        newForm.setUserRole(newUserRole);
        return formRepository.save(newForm);
    }

    public FormDTO getAvailableFormStates(final Long id) {
        final Form form = formRepository
                .findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form with id: " + id + " not found"));

        final FormDTO formDTO = new FormDTO();
        formDTO.setId(form.getId());
        formDTO.setUserRole(form.getUserRole());

        final FormState formState = form.getFormState();
        final FormStateFactory factory = new FormStateFactory();

        //нужно ли писать логику чтобы при выборе админа выставляло state ADMIN либо же заполняло если там null?
        if(formDTO.getUserRole().equals(ADMIN)) {
            formDTO.setCurrentState(factory.getFormState(ADMIN.name()));
            formDTO.setAvailableStates(formState.getAvailableStates(ADMIN));
        } else if(formDTO.getUserRole().equals(USER)) {
            formDTO.setCurrentState(formState);
            formDTO.setAvailableStates(formState.getAvailableStates(USER));
        } else {
            throw new EntityNotFoundException("Role " + formDTO.getUserRole() + " is not found");
        }

        return formDTO;
    }
}
