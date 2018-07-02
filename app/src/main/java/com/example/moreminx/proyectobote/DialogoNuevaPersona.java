package com.example.moreminx.proyectobote;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DialogoNuevaPersona extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(infService);

        final View layoutInflado = li.inflate(R.layout.nueva_persona, null);
        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());

        ventana.setTitle("Nueva Persona");
        ventana.setView(layoutInflado);

        //dentro del Spinner

        Spinner bebidas = layoutInflado.findViewById(R.id.spinner_nuevaPersona_bebida);
        final MainActivity activity = (MainActivity) getActivity();

        Integer[] id_nombres = ClavesBebidas.nombresBebidas();
        CharSequence[] nombres = new CharSequence[id_nombres.length];

        for (int i=0; i< id_nombres.length; i++){

            nombres[i]= getActivity().getResources().getString(id_nombres[i]);

        }

        bebidas.setAdapter(new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, nombres));
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                CharSequence nombre =((EditText) layoutInflado.findViewById(R.id.edit_nuevaPersona_nombre)).getText().toString();
                Integer telefono =Integer.parseInt(((EditText) layoutInflado.findViewById(R.id.edit_nuevaPersona_telefono)).getText().toString());
                Float saldo =Float.parseFloat(((EditText) layoutInflado.findViewById(R.id.edit_nuevaPersona_saldo)).getText().toString());
                ClavesBebidas bebidas =  ClavesBebidas.values()[((Spinner) layoutInflado.findViewById(R.id.spinner_nuevaPersona_bebida)).getSelectedItemPosition()];

                GestoraPersonas gestora = activity.getGestora();
                Persona persona = new Persona(nombre,telefono,saldo,bebidas);
               gestora.add(persona);
                activity.getAdaptador().notifyDataSetChanged();
                Toast.makeText(activity, "Persona añadida", Toast.LENGTH_LONG).show();
            }
        });

        return ventana.create();
    }
}
