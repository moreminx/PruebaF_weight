package com.example.moreminx.proyectobote;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DialogoEdicionTelefono extends DialogFragment {

    int numero =0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(infService);

        final View layoutInflado = li.inflate(R.layout.modificar_numero_telefono, null);

        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setTitle("Introduce el numero de Telefono");

        ventana.setView(layoutInflado);
        final MainActivity activity = (MainActivity) getActivity();

        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                CharSequence numeroNuevo = ((EditText)layoutInflado.findViewById(R.id.editText_ModificarNumero)).getText().toString();
                numero= numeroNuevo.length();
                if(numero == 9 ){
                    Persona personaAModificar = activity.getGestora().get(activity.getPosicion());
                    personaAModificar.put(ClavesPersona.telefono.toString(), numeroNuevo);
                    activity.getAdaptador().notifyDataSetChanged();


                }
                else{

                    Toast.makeText(activity, "el telefono debe tener 9 digitos", Toast.LENGTH_LONG).show();
                }





            }
        });
        return  ventana.create();
    }
}
