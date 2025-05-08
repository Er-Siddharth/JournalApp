package com.AsiaAutmation.JournalApp.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserRightsUpdateRequest {
    @NotEmpty(message = "Roles list must not be empty")
    private List<String> roles;
}
