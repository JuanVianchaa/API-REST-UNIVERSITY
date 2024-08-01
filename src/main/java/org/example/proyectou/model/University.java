package org.example.proyectou.model;

public class University {

    private String id;
    private String name;
    private String address;
    private String faculties;
    private String numberStudents;
    private String Program;

    public University(String id, String name, String address, String faculties, String numberStudents, String program) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.faculties = faculties;
        this.numberStudents = numberStudents;
        Program = program;
    }

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

    public String getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(String numberStudents) {
        this.numberStudents = numberStudents;
    }

    public String getProgram() {
        return Program;
    }

    public void setProgram(String program) {
        Program = program;
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", faculties='" + faculties + '\'' +
                ", numberStudents=" + numberStudents +
                ", Program='" + Program + '\'' +
                '}';
    }
}
