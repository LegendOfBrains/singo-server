package com.LegendOfBrains.singo_server.controller;

import com.LegendOfBrains.singo_server.dto.EnrollDTO;
import com.LegendOfBrains.singo_server.dto.ResponseDTO;
import com.LegendOfBrains.singo_server.enums.StateType;
import com.LegendOfBrains.singo_server.service.SingoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/singo")
public class SingoController {
    private final SingoService singoService;

    @GetMapping
    @Operation(summary = "신고 리스트 조회")
    public List<EnrollDTO> getAllReport() {
        List<EnrollDTO> enrollList = singoService.getAllEnroll();
        log.info("신고 리스트 조회 완료, 총 {}건", enrollList.size());
        return enrollList;
    }

    @GetMapping("/{reportId}")
    @Operation(summary = "신고 상세 조회")
    public ResponseDTO getReport(@RequestParam("reportId") Long reportId) {
        log.info("신고 ID: {}", reportId);
        EnrollDTO enrollDTO = singoService.getEnroll(reportId);
        if (enrollDTO != null) {
            return new ResponseDTO("신고 조회 완료");
        } else {
            return new ResponseDTO("해당 신고를 찾을 수 없습니다. ID: " + reportId);
        }
    }

    @PostMapping
    @Operation(summary = "신고 작성")
    public ResponseDTO postCreate(@RequestBody EnrollDTO enrollDTO) {
        log.info("신고 제목:{}", enrollDTO.getTitle());
        log.info("신고 내용:{}", enrollDTO.getContent());
        EnrollDTO savedEnroll = singoService.createEnroll(enrollDTO);
        return new ResponseDTO("신고 작성 완료");
    }

    @PutMapping("/state/{reportId}")
    @Operation(summary = "신고 처리 현황 변경")
    public ResponseDTO updateState(
            @RequestParam("reportId") Long reportId,
            @RequestParam("stateType") StateType stateType
    ) {
        log.info("수정 대상 신고 ID:{}", reportId);
        boolean updated = singoService.updateState(reportId, stateType);
        return new ResponseDTO(updated ? "updated" : "not found");
    }

    @DeleteMapping("/delete/{reportId}")
    @Operation(summary = "신고 삭제")
    public ResponseDTO delete(@RequestParam("reportId") Long reportId) {
        log.info("삭제 대상 신고 ID:{}", reportId);
        boolean deleted = singoService.delete(reportId);
        return new ResponseDTO(deleted ? "deleted" : "not found");
    }
}