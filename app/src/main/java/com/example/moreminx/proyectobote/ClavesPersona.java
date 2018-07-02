package com.example.moreminx.proyectobote;

/**
 * Created by ismao on 03/04/2018.
 */

public enum ClavesPersona
{
nombre,telefono,saldo,bebida;

public static String[] claves(){

String[] devolver = new String[values().length];

for (int i =0; i< devolver.length ; i++){

    devolver[i]=values()[i].toString();
}

return  devolver;

}


}
