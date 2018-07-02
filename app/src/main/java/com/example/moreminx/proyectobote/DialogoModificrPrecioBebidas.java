package com.example.moreminx.proyectobote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.content.Context.*;

public class DialogoModificrPrecioBebidas extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String infService = LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(infService);

        final View layoutInflado = li.inflate(R.layout.modificar_precio_bebida, null);

        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setTitle("Modificar Precio de la Bebida");
        ventana.setView(layoutInflado);

        //Preparar spinner//
        final Spinner spinner =layoutInflado.findViewById(R.id.spinner_modificarPrecioBebida_seleccionaBebida);
        final MainActivity activity = (MainActivity) getActivity();
        Integer[] id_bebidas = ClavesBebidas.nombresBebidas();
        CharSequence[] nombres = new CharSequence[id_bebidas.length];

        for (int i =0; i<id_bebidas.length; i++){

            nombres[i] = getActivity().getResources().getString(id_bebidas[i]);

        }
        spinner.setAdapter(new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, nombres));

        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ClavesBebidas bebidas = ClavesBebidas.values()[((Spinner) layoutInflado.findViewById(R.id.spinner_modificarPrecioBebida_seleccionaBebida)).getSelectedItemPosition()];
                String dato = ((EditText) layoutInflado.findViewById(R.id.edit_modificarPrecioBebida_nuevoPrecio)).getText().toString();

                if(dato.equals("")){

                    Toast.makeText(getActivity(), "el precio es incorrecto", Toast.LENGTH_LONG).show();
                }
                else {
                    Float precioNuevo = Float.parseFloat(dato);
                    SharedPreferences sharerd = getActivity().getSharedPreferences("Precios",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharerd.edit();
                    editor.putFloat( getResources().getString(bebidas.getNombre()),precioNuevo);
                    editor.commit();
                }









            }
        });

return ventana.create();

    }
}
