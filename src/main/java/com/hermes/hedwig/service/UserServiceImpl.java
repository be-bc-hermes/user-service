package com.hermes.hedwig.service;

import com.hermes.hedwig.exception.ResourceNotFoundException;
import com.hermes.hedwig.model.dto.UserDTO;
import com.hermes.hedwig.model.entity.User;
import com.hermes.hedwig.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User addUser(UserDTO userDTO){
      User userEntity = new User(userDTO);
      return userRepository.save(userEntity);
  }

  @Override
  public ResponseEntity<User> updateUser(UserDTO userDTO){
    userRepository.findById(userDTO.getObjectId()).orElse(null);
    // TODO: add null/exception handler

    User userEntity = new User(userDTO);
    userEntity = userRepository.save(userEntity);
    return new ResponseEntity<>(userEntity, HttpStatus.OK);
  }

  @Override
  public User getUserById(String id) {
    //  Optional<User>
    return userRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException(String.format("User not found with id: %s", id))
    );
  }

}
