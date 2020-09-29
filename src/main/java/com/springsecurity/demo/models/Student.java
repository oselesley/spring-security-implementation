package com.springsecurity.demo.models;

import lombok.Data;

@Data
public class Student {
    private Long id;
    private String name;
    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
