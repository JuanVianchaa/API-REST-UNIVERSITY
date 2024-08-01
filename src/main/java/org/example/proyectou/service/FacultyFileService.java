package org.example.proyectou.service;

import org.example.proyectou.model.Faculty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyFileService {
    private final String facultiesFilePath = "faculties.json"; // Ruta del archivo JSON de facultades
    private final ObjectMapper mapper = new ObjectMapper(); // Objeto para manejar JSON

    public List<Faculty> readFacultiesFromFile() throws IOException {
        File file = new File(facultiesFilePath);
        if (!file.exists()) {
            file.createNewFile(); // Crea el archivo si no existe
            return new ArrayList<>(); // Devuelve una lista vacía
        }
        return mapper.readValue(file, new TypeReference<List<Faculty>>() {}); // Devuelve la lista de facultades
    }

    public void writeFacultiesToFile(List<Faculty> faculties) throws IOException {
        mapper.writeValue(new File(facultiesFilePath), faculties); // Escribe la lista en el archivo JSON
    }
}
