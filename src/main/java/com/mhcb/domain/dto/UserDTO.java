package com.mhcb.domain.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String login;
    private String password;

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
