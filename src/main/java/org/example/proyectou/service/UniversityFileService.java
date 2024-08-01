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
    private final String universitiesFilePath = "universities.json"; // Ruta del archivo JSON de universidades
    private final String facultiesFilePath = "faculties.json"; // Ruta del archivo JSON de facultades
    private final ObjectMapper mapper = new ObjectMapper(); // Objeto para manejar JSON

    public List<University> readUniversitiesFromFile() throws IOException {
        File file = new File(universitiesFilePath);
        if (!file.exists()) {
            file.createNewFile(); // Crea el archivo si no existe
            return new ArrayList<>(); // Devuelve una lista vacía
        }
        return mapper.readValue(file, new TypeReference<List<University>>() {}); // Devuelve la lista de universidades
    }

    public void writeUniversitiesToFile(List<University> universities) throws IOException {
        mapper.writeValue(new File(universitiesFilePath), universities); // Escribe la lista en el archivo JSON
    }

    // Método para leer facultades desde el archivo JSON
    public List<Faculty> readFacultiesFromFile() throws IOException {
        File file = new File(facultiesFilePath);
        if (!file.exists()) {
            throw new IOException("Faculties file not found"); // Manejo de error si el archivo no existe
        }
        return mapper.readValue(file, new TypeReference<List<Faculty>>() {}); // Devuelve la lista de facultades
    }
}
