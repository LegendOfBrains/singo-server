package com.LegendOfBrains.singo_server.dto;

import com.LegendOfBrains.singo_server.enums.StateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrollResponseDTO {
    private Long reportId;
    private String title;
    private String content;
    private StateType stateType;
    private LocalDate date;
}
