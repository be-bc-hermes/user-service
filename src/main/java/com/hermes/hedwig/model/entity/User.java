package com.hermes.hedwig.model.entity;

import com.hermes.hedwig.model.dto.UserDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String objectId;

    @NotBlank
    private String fullName;

    @NotBlank
    @Indexed(unique = true)
    private String email;


    public User(UserDTO userDTO){
        this.setObjectId(userDTO.getObjectId());
        this.setFullName(userDTO.getFullName());
        this.setEmail(userDTO.getEmail());
    }


    public UserDTO toDTO(){
        return UserDTO.builder()
                .objectId(this.objectId)
                .fullName(this.fullName)
                .email(this.email)
                .build();
    }




}
