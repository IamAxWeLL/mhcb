package com.mhcb.controller;

import com.mhcb.database.dao.FormRepositoryDAO;
import com.mhcb.domain.Form;
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
    public Form addForm(@RequestParam final String state) {
        return formRepositoryDAO.add(state);
    }
}
