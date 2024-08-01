package org.example.proyectou.service;

import org.example.proyectou.model.Faculty;
import org.example.proyectou.model.University;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityFileService {

    private final String universitiesFilePath = "universities.json";
    private final String facultiesFilePath = "faculties.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<University> readUniversitiesFromFile() throws IOException {
        File file = new File(universitiesFilePath);
        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<>();
        }
        return mapper.readValue(file, new TypeReference<List<University>>() {
        });
    }

    public void writeUniversitiesToFile(List<University> universities) throws IOException {
        mapper.writeValue(new File(universitiesFilePath), universities);
    }

    public List<Faculty> readFacultiesFromFile() throws IOException {
        File file = new File(facultiesFilePath);
        if (!file.exists()) {
            throw new IOException("Faculties file not found");
        }
        return mapper.readValue(file, new TypeReference<List<Faculty>>() {
        });
    }
}