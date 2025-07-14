package com.example.model;

public class Department {
    private String code;
    private String name;

    // No-args constructor (required for Spring XML)
    public Department() {
    }

    // Parameterized constructor (optional, for manual usage)
    public Department(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // Getter and Setter for code
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Optional: toString() method for debugging
    @Override
    public String toString() {
        return "Department{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
