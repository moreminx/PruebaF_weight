package com.example.moreminx.proyectobote;

/**
 * Created by ismao on 03/04/2018.
 */

public enum ClavesBebidas {

    CAFE(R.string.CAFE,R.drawable.cafe,1.0f),
    BRANDY(R.string.BRANDY, R.drawable.brandy,2.5f),
    CERVEZA(R.string.CERVEZA, R.drawable.cerveza,1.2f),
    COCACOLA(R.string.COCACOLA, R.drawable.cocacola,2.0f),
    INFUSION(R.string.INFUSION, R.drawable.infusion,2.0f),
    VINO(R.string.VINO,R.drawable.vino,1.5f);



    private  final int nombre;
    private  final int imagen;
    private  final float precio;


    ClavesBebidas(int nombre, int imagen, float precio) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
    }

    public float getPrecio() {
        return precio;
    }

    public int getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }

public static Integer[] nombresBebidas(){

 Integer[] devolver = new Integer[values().length];


 for (int i=0; i< devolver.length; i++){

     devolver[i]=values()[i].getNombre();
 }

return  devolver;
}

public static ClavesBebidas devuelveImagen (int imagen){

    for (ClavesBebidas una: values()) {

        if (una.getImagen() == imagen){

            return una;

        }

    }
    return  null;
}

}
