package org.example.proyectou.service;

import org.example.proyectou.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private final String filePath = "users.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<User> readUsersFromFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<>();  // Devuelve una lista mutable
        }
        List<User> users = mapper.readValue(file, new TypeReference<List<User>>() {});
        return new ArrayList<>(users);  // Envuelve en una lista mutable
    }

    public void writeUsersToFile(List<User> users) throws IOException {
        mapper.writeValue(new File(filePath), users);
    }
}
