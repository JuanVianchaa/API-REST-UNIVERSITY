package org.example.proyectou.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String email;
    private String password;

}