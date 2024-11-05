package com.example.javarush.model.dto;

import lombok.Data;

import java.util.List;

/**
 * Студент
 */
@Data
public class StudentDTO {
    private Long id;

    private String name;

    private List<LectureDTO> lectures;

    private List<LecturerDTO> lecturers;
}
