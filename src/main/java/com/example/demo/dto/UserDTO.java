package com.example.demo.dto;

public class UserDTO {
    private String name;
    private int age;

    public UserDTO() {}

    public UserDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
