package com.example.moreminx.proyectobote;

import java.util.ArrayList;
import java.util.Collections;

public class GestoraPersonas extends ArrayList<Persona> {
    private static float cuota = 5;

    public static float getCuota() {
        return cuota;
    }

    public static void setCuota(float cuota) {
        GestoraPersonas.cuota = cuota;
    }

    public GestoraPersonas() {

        this.add(new Persona("alfredo",65546455,50,ClavesBebidas.BRANDY));
        this.add(new Persona("pepe",65546455,56,ClavesBebidas.CAFE));
        this.add(new Persona("carlos",65546455,2,ClavesBebidas.COCACOLA));
        this.add(new Persona("david",65546455,11,ClavesBebidas.INFUSION));

    }

    public void ordenar_pedir(){

        Collections.sort(this, new Ordenarpara_Pedir());
    }

    public void ordenar_saldo(){
        Collections.sort(this, new Ordenarpara_Saldo());
    }
}
