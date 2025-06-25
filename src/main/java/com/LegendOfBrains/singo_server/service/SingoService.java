package com.LegendOfBrains.singo_server.service;

import com.LegendOfBrains.singo_server.dto.EnrollDTO;
import com.LegendOfBrains.singo_server.entity.SinGo;
import com.LegendOfBrains.singo_server.repo.SinGoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SingoService {
    @Autowired
    SinGoRepository sinGoRepository;

    public EnrollDTO getEnroll(Long id) {
        Optional<SinGo> optional = sinGoRepository.findById(id);
        if(optional.isPresent()) {
            SinGo sinGo = optional.get();
            return convertToDTO(sinGo);
        }
        return null;
    }

    public List<EnrollDTO> getAllEnroll() {
        return sinGoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnrollDTO createEnroll(EnrollDTO enrollDTO) {
        SinGo sinGo = new SinGo();
        sinGo.setTitle(enrollDTO.getTitle());
        sinGo.setContent(enrollDTO.getContent());

        SinGo savedSinGo = sinGoRepository.save(sinGo);
        log.info("신고가 생성되었습니다. ID: {}, 제목: {}", savedSinGo.getReportId(), savedSinGo.getTitle());

        return convertToDTO(savedSinGo);
    }

    private EnrollDTO convertToDTO(SinGo sinGo) {
        EnrollDTO enrollDTO = new EnrollDTO();
        enrollDTO.setTitle(sinGo.getTitle());
        enrollDTO.setContent(sinGo.getContent());
        return enrollDTO;
    }
}
