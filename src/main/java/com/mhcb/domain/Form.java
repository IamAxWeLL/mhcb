package com.mhcb.domain;

import com.mhcb.core.state.FormState;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = FormConverter.class)
    private FormState formState;
}
