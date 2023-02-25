package com.container.loading.service;

import com.container.loading.dto.UserResponceDTO;
import com.container.loading.models.UserDTO;
import com.container.loading.repository.UserRepository;
import io.quarkus.runtime.Startup;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@RequiredArgsConstructor
@Startup
@ApplicationScoped
public class UserService {
    public final UserRepository userRepository;

    public void inserUser(UserDTO userDTO){
        userRepository.insertUser(userDTO);

    }
    public UserResponceDTO getUsers(){
        return userRepository.getUsers();
    }
    public void deleteUser(String user_id){
        userRepository.deleteUser(user_id);


    }

}
