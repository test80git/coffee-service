package com.example.javarush.service;

import com.example.javarush.mapper.StudentMapper;
import com.example.javarush.model.dto.StudentDTO;
import com.example.javarush.model.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyService {

    private final StudentMapper studentMapper;

   public void myServiceStudentToDTO(Student student) {
        StudentDTO dto = studentMapper.toDTO(student);
        System.out.println("MyService => "+dto);
    }

}
