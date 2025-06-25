package com.LegendOfBrains.singo_server.entity;

import com.LegendOfBrains.singo_server.enums.StateType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "SINGO")
public class SinGo {
    @Id
    private Long reportId;

    @Column(nullable = false)
    private String title; // 신고 제목

    @Column(nullable = false)
    private String content; // 신고 내용

    @Column(nullable = false)
    private StateType stateType; // 처리 현황

    @Column(nullable = false)
    private LocalDate date; // 신고일

    public void updateState(StateType stateType){
        this.stateType = stateType;
    }
}
