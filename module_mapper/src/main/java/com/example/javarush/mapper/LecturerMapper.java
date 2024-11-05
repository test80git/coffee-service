package com.example.javarush.mapper;

import com.example.javarush.model.dto.LecturerDTO;
import com.example.javarush.model.entity.Lecturer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LecturerMapper {
    LecturerDTO toDTO(Lecturer model);

    Lecturer toEntity(LecturerDTO dto);

    List<Lecturer> toEntityList(List<LecturerDTO> dtos);

    List<LecturerDTO> toDTOList(List<Lecturer> models);
}
