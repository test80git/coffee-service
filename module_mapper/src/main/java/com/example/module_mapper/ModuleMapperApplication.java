package com.example.module_mapper;

import com.example.javarush.model.entity.Lecture;
import com.example.javarush.model.entity.Lecturer;
import com.example.javarush.model.entity.Student;
import com.example.javarush.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.example")
public class ModuleMapperApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ModuleMapperApplication.class, args);

        Lecture lecture = run.getBean(Lecture.class);
        lecture.setId(1L);
        lecture.setName("Philosophical");

        Lecturer lecturer = run.getBean(Lecturer.class);
        lecturer.setId(1L);
        lecturer.setName("Anna");

        Lecture lecture2 = run.getBean(Lecture.class);
        lecture.setId(2L);
        lecture.setName("Geography");

        Lecturer lecturer2 = run.getBean(Lecturer.class);
        lecturer.setId(2L);
        lecturer.setName("Tomara");

        Student student = run.getBean(Student.class);
        student.setId(1L);
        student.setName("Bob");
        student.setLectures(List.of(lecture, lecture2));
        student.setLecturers(List.of(lecturer, lecturer2));

        System.out.println(student+"\n");

        MyService bean = run.getBean(MyService.class);
        bean.myServiceStudentToDTO(student);

        run.close();
    }

}
