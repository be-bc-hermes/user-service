package com.hermes.hedwig.service;

import com.hermes.hedwig.exception.ResourceNotFoundException;
import com.hermes.hedwig.messageBroker.Message;
import com.hermes.hedwig.messageBroker.Producer;
import com.hermes.hedwig.model.dto.CommunicationInfoDTO;
import com.hermes.hedwig.model.dto.UserDTO;
import com.hermes.hedwig.model.entity.User;
import com.hermes.hedwig.model.mapper.UserMapper;
import com.hermes.hedwig.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Producer producer;


    @Override
    public User addUser(UserDTO userDTO) {
        User userEntity = new User(userDTO);
        return userRepository.save(userEntity);
    }

    @Override
    public ResponseEntity<User> updateUser(UserDTO userDTO) {
        User user = this.getUserById(userDTO.getId());
        String oldCommInfo = user.getEmail();
        UserMapper.INSTANCE.updateUserFromDTO(userDTO, user);
        userRepository.save(user);
        notifyServicesWhenCommunicationInfoChange(oldCommInfo, userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("User not found with id: %s", id))
        );
    }


    private void notifyServicesWhenCommunicationInfoChange(String commInfo, UserDTO userDTO) {

        if (!userDTO.getEmail().isEmpty() &&
            !commInfo.equals(userDTO.getEmail())) {

            CommunicationInfoDTO communicationInfoDTO = CommunicationInfoDTO.builder()
                                                        .id(userDTO.getId())
                                                        .email(userDTO.getEmail())
                                                        .build();
            Message message = Message.builder()
                                    .id(UUID.randomUUID().toString())
                                    .message(communicationInfoDTO)
                                    .build();

            producer.send(message);
        }
    }

}
