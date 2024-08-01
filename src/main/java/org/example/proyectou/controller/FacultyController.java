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

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable String id) {
        try {
            List<Faculty> faculties = fileService.readFacultiesFromFile();
            Optional<Faculty> faculty = faculties.stream().filter(f -> f.getId().equals(id)).findFirst();
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
            fileService.addFaculty(faculty); // Llama al método del servicio para agregar la facultad
            return ResponseEntity.status(201).body(faculty);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable String id, @RequestBody Faculty faculty) {
        try {
            List<Faculty> faculties = fileService.readFacultiesFromFile();
            Optional<Faculty> existingFaculty = faculties.stream().filter(f -> f.getId().equals(id)).findFirst();
            if (existingFaculty.isPresent()) {
                Faculty updatedFaculty = existingFaculty.get();
                updatedFaculty.setName(faculty.getName()); // Actualizar el nombre
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable String id) {
        try {
            List<Faculty> faculties = fileService.readFacultiesFromFile();
            if (faculties.removeIf(f -> f.getId().equals(id))) {
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
