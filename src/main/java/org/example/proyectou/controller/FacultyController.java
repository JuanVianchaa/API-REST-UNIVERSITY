package org.example.proyectou.controller;

import org.example.proyectou.model.Faculty;
import org.example.proyectou.service.FacultyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faculties")
public class FacultyController {

    @Autowired
    private FacultyFileService fileService;

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        try {
            List<Faculty> faculties = fileService.readFacultiesFromFile();
            return ResponseEntity.ok(faculties);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Faculty> getFacultyByName(@PathVariable String name) {
        try {
            List<Faculty> faculties = fileService.readFacultiesFromFile();
            Optional<Faculty> faculty = faculties.stream().filter(f -> f.getName().equalsIgnoreCase(name)).findFirst();
            if (faculty.isPresent()) {
                return ResponseEntity.ok(faculty.get());
            } else {
                return ResponseEntity.status(404).build(); // Facultad no encontrada
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        try {
            List<Faculty> faculties = fileService.readFacultiesFromFile();
            faculties.add(faculty); // Agrega la nueva facultad
            fileService.writeFacultiesToFile(faculties);
            return ResponseEntity.status(201).body(faculty);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable String name, @RequestBody Faculty faculty) {
        try {
            List<Faculty> faculties = fileService.readFacultiesFromFile();
            Optional<Faculty> existingFaculty = faculties.stream().filter(f -> f.getName().equalsIgnoreCase(name)).findFirst();
            if (existingFaculty.isPresent()) {
                Faculty updatedFaculty = existingFaculty.get();
                updatedFaculty.setDepartment(faculty.getDepartment());
                updatedFaculty.setEmail(faculty.getEmail());
                fileService.writeFacultiesToFile(faculties);
                return ResponseEntity.ok(updatedFaculty);
            } else {
                return ResponseEntity.status(404).build(); // Facultad no encontrada
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable String name) {
        try {
            List<Faculty> faculties = fileService.readFacultiesFromFile();
            if (faculties.removeIf(f -> f.getName().equalsIgnoreCase(name))) {
                fileService.writeFacultiesToFile(faculties);
                return ResponseEntity.status(204).build(); // Eliminación exitosa
            } else {
                return ResponseEntity.status(404).build(); // Facultad no encontrada
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
