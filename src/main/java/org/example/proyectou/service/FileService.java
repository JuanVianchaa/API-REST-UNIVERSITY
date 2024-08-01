package org.example.proyectou.service;

import org.example.proyectou.model.User;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private static final String FILE_NAME = "users.ser";

    public List<User> readUsersFromFile() throws IOException {
        List<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Si el archivo no existe, retorna una lista vacía
        } catch (ClassNotFoundException e) {
            throw new IOException("Error al leer el archivo de usuarios", e);
        }
        return users;
    }

    public void writeUsersToFile(List<User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        }
    }
}
