package org.example.proyectou.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Faculty {

    private String id;
    private String name;
    private String department;
    private String email;

    @Override
    public String toString() {
        return "Faculty{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}