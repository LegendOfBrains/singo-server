package com.LegendOfBrains.singo_server.controller;

import com.LegendOfBrains.singo_server.dto.EnrollDTO;
import com.LegendOfBrains.singo_server.dto.ResponseDTO;
import com.LegendOfBrains.singo_server.service.SingoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SingoController {
    private final SingoService singoService;

    @GetMapping("/singo")
    public List<EnrollDTO> getAllReport() {
        List<EnrollDTO> enrollList = singoService.getAllEnroll();
        log.info("신고 리스트 조회 완료, 총 {}건", enrollList.size());
        return enrollList;
    }

    @GetMapping("/singo/{id}")
    public ResponseDTO getReport(@RequestParam("reportId") Long reportId) {
        log.info("신고 ID: {}", reportId);
        EnrollDTO enrollDTO = singoService.getEnroll(reportId);
        if (enrollDTO != null) {
            return new ResponseDTO("신고 조회 완료", enrollDTO);
        } else {
            return new ResponseDTO("해당 신고를 찾을 수 없습니다. ID: ", enrollDTO);
        }
    }

    @PostMapping("/singo")
    public ResponseDTO postCreate(@RequestBody EnrollDTO enrollDTO) {
        log.info("신고 제목:{}", enrollDTO.getTitle());
        log.info("신고 내용:{}", enrollDTO.getContent());
        EnrollDTO savedEnroll = singoService.createEnroll(enrollDTO);
        return new ResponseDTO("신고 작성 완료", savedEnroll);
    }
}