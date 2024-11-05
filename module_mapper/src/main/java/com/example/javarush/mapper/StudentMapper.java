package com.example.javarush.mapper;

import com.example.javarush.model.dto.StudentDTO;
import com.example.javarush.model.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring"
//        , uses = {LectureListMapper.class, LecturerMapper.class}
)
public interface StudentMapper {

    Student toEntity(StudentDTO dto);

    StudentDTO toDTO(Student model);

}
