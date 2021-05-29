package com.mhcb.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@Data
public class Person {

    private String login;
    private String password;
    private String surnameUa;
    private String surnameEn;
    private String nameUa;
    private String nameEn;
    private String patronymicUa;
    private String patronymicEn;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;
    private String phoneNumber;
    private String email;

    @Transient
    public String getFullName() {
        return surnameEn + " " + nameEn + " " + patronymicEn;
    }
}
