package com.LegendOfBrains.singo_server.controller;

import com.LegendOfBrains.singo_server.dto.EnrollDTO;
import com.LegendOfBrains.singo_server.dto.EnrollResponseDTO;
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
    public List<EnrollResponseDTO> getAllReport() {
        List<EnrollResponseDTO> enrollList = singoService.getAllEnroll();
        log.info("신고 리스트 조회 완료, 총 {}건", enrollList.size());
        return enrollList;
    }

    @GetMapping("/{reportId}")
    @Operation(summary = "신고 상세 조회")
    public EnrollResponseDTO getReport(@RequestParam("reportId") Long reportId) {
        log.info("신고 ID: {}", reportId);
        return singoService.getEnroll(reportId);
    }

    @PostMapping
    @Operation(summary = "신고 작성")
    public ResponseDTO postCreate(@RequestBody EnrollDTO enrollDTO) {
        log.info("신고 제목:{}", enrollDTO.getTitle());
        log.info("신고 내용:{}", enrollDTO.getContent());
        boolean created = singoService.createEnroll(enrollDTO);
        return new ResponseDTO(created ? "신고가 접수되었습니다." : "신고 접수에 실패하였습니다.");
    }

    @PutMapping("/state/{reportId}")
    @Operation(summary = "신고 처리 현황 변경")
    public ResponseDTO updateState(
            @RequestParam("reportId") Long reportId,
            @RequestParam("stateType") StateType stateType
    ) {
        log.info("수정 대상 신고 ID:{}", reportId);
        boolean updated = singoService.updateState(reportId, stateType);
        return new ResponseDTO(updated ? "신고 처리 현황을 변경하였습니다." : "신고 처리 현황 변경에 실패하였습니다.");
    }

    @DeleteMapping("/delete/{reportId}")
    @Operation(summary = "신고 삭제")
    public ResponseDTO delete(@RequestParam("reportId") Long reportId) {
        log.info("삭제 대상 신고 ID:{}", reportId);
        boolean deleted = singoService.delete(reportId);
        return new ResponseDTO(deleted ? "신고가 삭제되었습니다." : "해당 신고를 찾을 수 없습니다.");
    }
}