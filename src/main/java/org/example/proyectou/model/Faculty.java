package org.example.proyectou.model;

public class Faculty {
    private String name;
    private String department;
    private String email;

    // Constructor
    public Faculty(String name, String department, String email) {
        this.name = name;
        this.department = department;
        this.email = email;
    }

    public Faculty() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
