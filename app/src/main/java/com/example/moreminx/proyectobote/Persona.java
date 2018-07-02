package com.example.moreminx.proyectobote;

import java.util.HashMap;

/**
 * Created by ismao on 03/04/2018.
 */

public class Persona extends HashMap<String,Object> {

    public Persona(CharSequence nombre, int telefono, float saldo, ClavesBebidas bebidas){

        super();
        this.put(ClavesPersona.nombre.toString(), nombre);
        this.put(ClavesPersona.telefono.toString(), telefono);
        this.put(ClavesPersona.saldo.toString(),saldo);
        this.put(ClavesPersona.bebida.toString(),bebidas);

    }


}
