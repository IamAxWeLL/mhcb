package com.mhcb.domain;

import com.mhcb.core.state.FormState;
import com.mhcb.domain.dto.Address;
import com.mhcb.domain.dto.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "forms")
@AllArgsConstructor
@NoArgsConstructor
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = FormConverter.class)
    private FormState formState;

    @Embedded
    private Person person;
    @Embedded
    private Address address;
}
