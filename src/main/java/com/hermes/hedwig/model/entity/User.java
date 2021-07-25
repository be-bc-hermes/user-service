package com.hermes.hedwig.model.entity;

import com.hermes.hedwig.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String fullName;

    @Column(unique = true)
    private String email;


    public User(UserDTO userDTO) {
        this.setId(userDTO.getId());
        this.setFullName(userDTO.getFullName());
        this.setEmail(userDTO.getEmail());
    }


    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(this.id)
                .fullName(this.fullName)
                .email(this.email)
                .build();
    }


}
