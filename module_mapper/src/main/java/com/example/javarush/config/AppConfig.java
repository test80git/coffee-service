package com.example.javarush.config;

import com.example.javarush.model.dto.StudentDTO;
import com.example.javarush.model.entity.Lecture;
import com.example.javarush.model.entity.Lecturer;
import com.example.javarush.model.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Lecture lecture() {
        return new Lecture();
    }

    @Bean
    public Lecturer lecturer() {
        return new Lecturer();
    }

    @Bean
    public Student student() {
        return new Student();
    }

    @Bean
    public StudentDTO studentDTO() {
        return new StudentDTO();
    }
}
