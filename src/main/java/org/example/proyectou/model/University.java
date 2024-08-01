package org.example.proyectou.model;

import java.util.List;

public class University {
    private String id;
    private String name;
    private String address;
    private String faculties; // Asegúrate de que sea una lista
    private int numberStudents;
    private String program;

    // Constructor


    public University(String id, String name, String address, String faculties, int numberStudents, String program) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.faculties = faculties;
        this.numberStudents = numberStudents;
        this.program = program;
    }

    // Constructor vacío
    public University() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaculties() {
        return faculties;
    }

    public void setFaculties(String faculties) {
        this.faculties = faculties;
    }

    public int getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(int numberStudents) {
        this.numberStudents = numberStudents;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

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
