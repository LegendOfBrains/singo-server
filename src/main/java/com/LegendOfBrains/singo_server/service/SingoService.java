package com.LegendOfBrains.singo_server.service;

import com.LegendOfBrains.singo_server.dto.EnrollDTO;
import com.LegendOfBrains.singo_server.dto.EnrollResponseDTO;
import com.LegendOfBrains.singo_server.entity.SinGo;
import com.LegendOfBrains.singo_server.enums.StateType;
import com.LegendOfBrains.singo_server.repo.SinGoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SingoService {
    @Autowired
    SinGoRepository sinGoRepository;

    public EnrollResponseDTO getEnroll(Long id) {
        Optional<SinGo> optional = sinGoRepository.findById(id);
        if(optional.isPresent()) {
            SinGo sinGo = optional.get();
            return convertToDTO(sinGo);
        }
        return null;
    }

    public List<EnrollResponseDTO> getAllEnroll() {
        return sinGoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public boolean createEnroll(EnrollDTO enrollDTO) {
        Optional<SinGo> optional = sinGoRepository.findById(enrollDTO.getReportId());
        if (optional.isPresent()) {
            return false;
        }
        SinGo sinGo = new SinGo();
        sinGo.setReportId(enrollDTO.getReportId());
        sinGo.setTitle(enrollDTO.getTitle());
        sinGo.setContent(enrollDTO.getContent());
        sinGo.setDate(LocalDate.now());
        sinGo.setStateType(StateType.WAITING);

        SinGo savedSinGo = sinGoRepository.save(sinGo);
        log.info("신고가 생성되었습니다. ID: {}, 제목: {}", savedSinGo.getReportId(), savedSinGo.getTitle());
        return true;
    }

    public Boolean updateState(Long id, StateType stateType) {
        Optional<SinGo> optional = sinGoRepository.findById(id);
        if(optional.isPresent()) {
            SinGo sinGo = optional.get();
            sinGo.updateState(stateType);
            sinGoRepository.save(sinGo);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if(sinGoRepository.existsById(id)) {
            sinGoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private EnrollResponseDTO convertToDTO(SinGo sinGo) {
        EnrollResponseDTO responseDTO = new EnrollResponseDTO();
        responseDTO.setReportId(sinGo.getReportId());
        responseDTO.setTitle(sinGo.getTitle());
        responseDTO.setContent(sinGo.getContent());
        responseDTO.setStateType(sinGo.getStateType());
        responseDTO.setDate(sinGo.getDate());
        return responseDTO;
    }
}
