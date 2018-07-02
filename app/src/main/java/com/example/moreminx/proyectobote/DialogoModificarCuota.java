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

public class DialogoModificarCuota extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(infService);
        final View layoutInflado = li.inflate(R.layout.modificar_cuota, null);

        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setTitle("Nueva Cuota");
        ventana.setView(layoutInflado);

        ventana.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

              String dato =((EditText) layoutInflado.findViewById(R.id.edit_modificarCuota_nuevoValor)).getText().toString();

                if (dato.equals("")){


                    Toast.makeText(getActivity(), "la cuota es incorrecta", Toast.LENGTH_LONG).show();
                } else {
                    Float nuevaCuota =Float.parseFloat(dato);
                    GestoraPersonas gestora = MainActivity.getGestora();
                    gestora.setCuota(nuevaCuota);
                }

            }
        });

return ventana.create();

    }
}
