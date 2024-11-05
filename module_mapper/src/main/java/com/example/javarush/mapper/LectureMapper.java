package com.example.javarush.mapper;

import com.example.javarush.model.dto.LectureDTO;
import com.example.javarush.model.entity.Lecture;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LectureMapper {
    LectureDTO toDTO(Lecture model);

    Lecture toEntity(LectureDTO dto);
}
