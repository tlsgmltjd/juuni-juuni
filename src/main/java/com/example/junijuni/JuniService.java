package com.example.junijuni;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JuniService {
    private final JuniRepository juniRepository;
    private final JuniListRepository juniListRepository;

    public void execute(Dto dto) {
        juniRepository.deleteAll();
        juniRepository.save(JuniEntity.builder()
                        .id(1L)
                        .message(dto.getMessage())
                        .type(dto.getType())
                        .timestamp(dto.getTimestamp())
                .build());
        juniListRepository.save(JuniListEntity.builder()
                        .message(dto.getMessage())
                .build());
    }

    public Optional<Dto> dataGet() {
        Optional<JuniEntity> juniEntity = juniRepository.findById(1L);
        return juniEntity.map(entity -> new Dto(entity.getType(), entity.getMessage(), entity.getTimestamp()));
    }

    public void dataDelete() {
        juniRepository.deleteById(1L);
    }

    public List<JuniListDto> listGet() {
        List<JuniListEntity> juniListEntities = juniListRepository.findAll();
        return juniListEntities.stream()
                .map(juniListEntity -> new JuniListDto(juniListEntity.getId(), juniListEntity.getMessage())).toList();
    }

    public void listDelete() {
        juniListRepository.deleteAll();
    }
}
