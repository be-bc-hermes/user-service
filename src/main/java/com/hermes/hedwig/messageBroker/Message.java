package com.hermes.hedwig.messageBroker;

import com.hermes.hedwig.model.dto.CommunicationInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    String id;
    CommunicationInfoDTO message;
}
