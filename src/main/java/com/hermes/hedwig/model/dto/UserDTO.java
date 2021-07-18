package com.hermes.hedwig.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
public class UserDTO {

    private String objectId;

    private String fullName;

    private String email;

    // comm info contains?
}
