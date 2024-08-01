package org.example.proyectou.service;

import org.example.proyectou.model.Faculty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FacultyFileService {

    private final String facultiesFilePath = "faculties.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Faculty> readFacultiesFromFile() throws IOException {
        File file = new File(facultiesFilePath);
        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<>();
        }
        return mapper.readValue(file, new TypeReference<List<Faculty>>() {
        });
    }

    public void writeFacultiesToFile(List<Faculty> faculties) throws IOException {
        mapper.writeValue(new File(facultiesFilePath), faculties);
    }

    public void addFaculty(Faculty faculty) throws IOException {
        List<Faculty> faculties = readFacultiesFromFile();
        faculty.setId(UUID.randomUUID().toString());
        faculties.add(faculty);
        writeFacultiesToFile(faculties);
    }
}