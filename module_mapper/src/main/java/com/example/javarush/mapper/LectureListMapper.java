package com.example.javarush.mapper;

import com.example.javarush.model.dto.LectureDTO;
import com.example.javarush.model.entity.Lecture;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = LectureMapper.class)
public interface LectureListMapper {
    List<Lecture> toEntityList(List<LectureDTO> dtos);
    List<LectureDTO> toDTOList(List<Lecture> models);
}
