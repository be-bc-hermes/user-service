package com.hermes.hedwig.service;

import com.hermes.hedwig.model.dto.UserDTO;
import com.hermes.hedwig.model.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User addUser(UserDTO userDTO);

    ResponseEntity<User> updateUser(UserDTO userDTO);

    User getUserById(Long id);
}
