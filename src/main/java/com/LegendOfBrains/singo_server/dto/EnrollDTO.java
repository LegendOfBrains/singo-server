package com.LegendOfBrains.singo_server.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrollDTO {
    private Long reportId;
    private String title;
    private String content;
}