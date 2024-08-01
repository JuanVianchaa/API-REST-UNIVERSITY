package org.example.proyectou.service;

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
    private final String filePath = "universities.json"; // Ruta del archivo JSON
    private final ObjectMapper mapper = new ObjectMapper(); // Objeto para manejar JSON

    public List<University> readUniversitiesFromFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile(); // Crea el archivo si no existe
            return new ArrayList<>(); // Devuelve una lista vacía
        }
        List<University> universities = mapper.readValue(file, new TypeReference<List<University>>() {});
        return new ArrayList<>(universities); // Devuelve una lista mutable
    }

    public void writeUniversitiesToFile(List<University> universities) throws IOException {
        mapper.writeValue(new File(filePath), universities); // Escribe la lista en el archivo JSON
    }
}
