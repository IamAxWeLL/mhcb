package com.mhcb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhcb.database.dao.FormRepositoryDAO;
import com.mhcb.domain.Locality;
import com.mhcb.domain.UserRole;
import com.mhcb.domain.dto.Address;
import com.mhcb.domain.dto.FormDTO;
import com.mhcb.domain.dto.Person;
import com.mhcb.domain.dto.ProfileDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FormController.class})
@ExtendWith(SpringExtension.class)
public class FormControllerTest {
    @Autowired
    private FormController formController;

    @MockBean
    private FormRepositoryDAO formRepositoryDAO;

    @Test
    public void shouldFillInForm() throws Exception {
        when(this.formRepositoryDAO.getAvailableFormStates((Long) any(), (UserRole) any()))
                .thenReturn(new FormDTO());

        Address address = new Address();
        address.setBlock(1L);
        address.setStreetType("Street Type");
        address.setDistrict("District");
        address.setRegion("us-east-2");
        address.setLocalityType(Locality.CITY);
        address.setBuildingNumber("Building Number");
        address.setLocalityName("Locality Name");

        Person person = new Person();
        person.setLogin("Login");
        person.setPassword("iloveyou");
        person.setEmail("jane.doe@example.org");
        person.setPatronymicUa("Patronymic Ua");
        person.setNameUa("Name Ua");
        person.setPhoneNumber("4105551212");
        person.setSurnameEn("Doe");
        person.setSurnameUa("Doe");
        person.setNameEn("Name En");
        person.setPatronymicEn("Patronymic En");

        FormDTO formDTO = new FormDTO();
        formDTO.setId(123L);
        formDTO.setAddress(address);
        formDTO.setPerson(person);
        formDTO.setAvailableStates(new ArrayList<String>());
        formDTO.setCurrentState("Current State");
        String content = (new ObjectMapper()).writeValueAsString(formDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/forms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.formController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"id\":null,\"currentState\":null,\"availableStates\":null,\"address\":null,\"person\":null}")));
    }

    @Test
    public void shouldGetForm() throws Exception {
        when(this.formRepositoryDAO.getAvailableFormStates((Long) any(), (UserRole) any())).thenReturn(new FormDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/forms");
        MockHttpServletRequestBuilder paramResult = getResult.param("id", String.valueOf(1L));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("userRole", String.valueOf(UserRole.ADMIN));
        MockMvcBuilders.standaloneSetup(this.formController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"id\":null,\"currentState\":null,\"availableStates\":null,\"address\":null,\"person\":null}")));
    }

    @Test
    public void shouldGetProfile() throws Exception {
        when(this.formRepositoryDAO.getProfileByFormId((Long) any())).thenReturn(new ProfileDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/forms/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.formController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString("{\"fullName\":null,\"fullAddress\":null}")));
    }
}

