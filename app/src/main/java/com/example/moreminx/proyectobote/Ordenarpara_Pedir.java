package com.example.moreminx.proyectobote;

import java.util.Comparator;

public class Ordenarpara_Pedir implements Comparator<Persona> {
    @Override
    public int compare(Persona p1, Persona p2) {
        return  p1.get(ClavesPersona.bebida.toString()).toString().compareTo(p2.get(ClavesPersona.bebida.toString()).toString());
    }
}
