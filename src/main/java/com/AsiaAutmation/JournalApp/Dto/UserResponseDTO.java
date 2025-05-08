package com.AsiaAutmation.JournalApp.Dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private String userName;
    private String email;
    private List<String> roles;

}
