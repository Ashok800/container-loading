package com.container.loading.dto;

import com.container.loading.models.UserDTO;
import lombok.Data;

import java.util.List;
@Data

public class UserResponceDTO {

    private List<UserDTO> users;

}
