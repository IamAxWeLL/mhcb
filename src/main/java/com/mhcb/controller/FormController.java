package com.mhcb.controller;

import com.mhcb.database.dao.FormRepositoryDAO;
import com.mhcb.domain.UserRole;
import com.mhcb.domain.dto.FormDTO;
import com.mhcb.domain.dto.ProfileDTO;
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
    public void fillInForm(final @RequestBody FormDTO formDTO) {
        formRepositoryDAO.addForm(formDTO);
    }

    @GetMapping
    public FormDTO getForm(final Long id, final UserRole userRole) {
        return formRepositoryDAO.getAvailableFormStates(id, userRole);
    }

    @GetMapping("/{id}")
    public ProfileDTO getProfile(@PathVariable final Long id) {
        return formRepositoryDAO.getProfileByFormId(id);
    }
}
