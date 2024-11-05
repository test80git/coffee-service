package com.example.core.studentAvro;


public class AvroRequest {

    private String name ;
    private int age;
    private String course;

    public AvroRequest() {
    }

    public AvroRequest(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "AvroRequest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", course='" + course + '\'' +
                '}';
    }
}
