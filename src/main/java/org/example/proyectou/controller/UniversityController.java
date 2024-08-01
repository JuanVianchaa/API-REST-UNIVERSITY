package org.example.proyectou.controller;

import org.example.proyectou.model.University;
import org.example.proyectou.model.Faculty;
import org.example.proyectou.service.UniversityFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityFileService fileService;

    @GetMapping
    public ResponseEntity<List<University>> getAllUniversities() {
        try {
            List<University> universities = fileService.readUniversitiesFromFile(); // Método para leer universidades del archivo
            return ResponseEntity.ok(universities);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable String id) {
        try {
            List<University> universities = fileService.readUniversitiesFromFile();
            Optional<University> university = universities.stream().filter(u -> u.getId().equals(id)).findFirst();
            if (university.isPresent()) {
                return ResponseEntity.ok(university.get());
            } else {
                return ResponseEntity.status(404).build(); // Universidad no encontrada
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<University> addUniversity(@RequestBody University university) {
        try {
            List<University> universities = fileService.readUniversitiesFromFile();
            university.setId(UUID.randomUUID().toString()); // Genera un ID único para la universidad
            universities.add(university);
            fileService.writeUniversitiesToFile(universities); // Método para guardar universidades en el archivo
            return ResponseEntity.status(201).body(university);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable String id, @RequestBody University university) {
        try {
            List<University> universities = fileService.readUniversitiesFromFile();
            Optional<University> existingUniversity = universities.stream().filter(u -> u.getId().equals(id)).findFirst();
            if (existingUniversity.isPresent()) {
                University updatedUniversity = existingUniversity.get();
                updatedUniversity.setName(university.getName());
                updatedUniversity.setAddress(university.getAddress());
                updatedUniversity.setFaculties(university.getFaculties());
                updatedUniversity.setNumberStudents(university.getNumberStudents());
                updatedUniversity.setProgram(university.getProgram());
                fileService.writeUniversitiesToFile(universities);
                return ResponseEntity.ok(updatedUniversity);
            } else {
                return ResponseEntity.status(404).build(); // Universidad no encontrada
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable String id) {
        try {
            List<University> universities = fileService.readUniversitiesFromFile();
            if (universities.removeIf(u -> u.getId().equals(id))) {
                fileService.writeUniversitiesToFile(universities);
                return ResponseEntity.status(204).build(); // Eliminación exitosa
            } else {
                return ResponseEntity.status(404).build(); // Universidad no encontrada
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/faculties")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        try {
            List<Faculty> faculties = fileService.readFacultiesFromFile(); // Lee facultades desde el archivo
            return ResponseEntity.ok(faculties);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build(); // Manejo de error
        }
    }
}
