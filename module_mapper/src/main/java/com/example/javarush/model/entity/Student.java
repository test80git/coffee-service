package com.example.javarush.model.entity;

import lombok.Data;

import java.util.List;

/**
 * Студент
 */
@Data
public class Student {
    private Long id;
    private String name;
    private List<Lecture> lectures;
    private List<Lecturer> lecturers;
}
