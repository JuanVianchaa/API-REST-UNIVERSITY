package org.example.proyectou.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class University {
    private String id;
    private String name;
    private String address;
    private String faculties;
    private int numberStudents;
    private String program;

    @Override
    public String toString() {
        return "University{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", faculties=" + faculties +
                ", numberStudents=" + numberStudents +
                ", program='" + program + '\'' +
                '}';
    }
}