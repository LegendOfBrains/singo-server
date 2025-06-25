package com.LegendOfBrains.singo_server.dto;

import com.LegendOfBrains.singo_server.enums.StateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateStateDTO {
    private Long reportId;
    private StateType stateType;
}
