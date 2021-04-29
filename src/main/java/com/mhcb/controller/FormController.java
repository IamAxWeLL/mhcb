package com.mhcb.controller;

import com.mhcb.database.dao.FormRepositoryDAO;
import com.mhcb.domain.State;
import com.mhcb.domain.UserRole;
import com.mhcb.domain.dto.FormDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormRepositoryDAO formRepositoryDAO;

    public FormController(final FormRepositoryDAO formRepositoryDAO) {
        this.formRepositoryDAO = formRepositoryDAO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addForm(final State state) {
        formRepositoryDAO.add(state);
    }

    @GetMapping
    public FormDTO getForm(final Long id, final UserRole userRole) {
        return formRepositoryDAO.getAvailableFormStates(id, userRole);
    }
}
