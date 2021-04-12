package com.mhcb.database.dao;

import com.mhcb.database.repository.FormRepository;
import com.mhcb.domain.Form;
import com.mhcb.domain.FormStateMicroFactory;
import org.springframework.stereotype.Repository;

@Repository
public class FormRepositoryDAO {

    private final FormRepository formRepository;

    public FormRepositoryDAO(final FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public Form add(String state) {
        final Form newForm = new Form();
        FormStateMicroFactory formStateMicroFactory = new FormStateMicroFactory();
        newForm.setFormState(formStateMicroFactory.getFormStateMicro(state));
        return formRepository.save(newForm);
    }
}
