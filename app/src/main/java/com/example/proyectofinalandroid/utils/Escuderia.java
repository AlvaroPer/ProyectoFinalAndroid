package com.example.proyectofinalandroid.utils;

import java.io.Serializable;
import java.util.ArrayList;

public class Escuderia implements Serializable {

    private String nombre;

    public Escuderia(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
