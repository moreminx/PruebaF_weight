package com.example.moreminx.proyectobote;

import java.util.Comparator;

public class Ordenarpara_Saldo implements Comparator<Persona> {


    @Override
    public int compare(Persona p1, Persona p2) {
        return  p1.get(ClavesPersona.saldo.toString()).toString().compareTo(p2.get(ClavesPersona.saldo.toString()).toString());
    }
}
