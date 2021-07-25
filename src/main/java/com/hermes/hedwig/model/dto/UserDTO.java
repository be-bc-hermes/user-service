package com.hermes.hedwig.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Long id;

    private String fullName;

    private String email;

    // comm info contains?
}
