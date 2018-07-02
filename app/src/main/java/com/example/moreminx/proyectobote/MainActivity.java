package com.example.moreminx.proyectobote;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static ListView lista;
    public static SimpleAdapter adaptador;
    public static GestoraPersonas gestora;
    private int posi;


    public int getPosicion() {
        return posicion;
    }

    private int posicion;

    public static SimpleAdapter getAdaptador() {
        return adaptador;
    }

    public static GestoraPersonas getGestora() {
        return gestora;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        leerFichero();
        Shared();
        registerForContextMenu(lista);


    }

    private void leerFichero() {

        try {
            FileInputStream fis = openFileInput("personas");
            ObjectInputStream in = new ObjectInputStream(fis);
            gestora = (GestoraPersonas) in.readObject();
            fis.close();
            in.close();
        } catch (Exception e) {
            gestora = new GestoraPersonas();
        }
        crearLista();


    }

    private void crearLista() {
        String[] from = ClavesPersona.claves();
        lista = this.findViewById(R.id.lista);

        //nombre,telefono,saldo,bebida;
        int[] to = {R.id.text_nombre, R.id.text_telefono, R.id.text_dinero, R.id.imagen_bebida};
        adaptador = new SimpleAdapter(this, gestora, R.layout.componenteslista, from, to);
        adaptador.setViewBinder(new DatosViewAdapter());
        lista.setAdapter(adaptador);
        lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    private void escribirFichero() {

        try {
            FileOutputStream fo = openFileOutput("personas", Context.MODE_PRIVATE);
            ObjectOutputStream ou = new ObjectOutputStream(fo);
            ou.writeObject(gestora);
            ou.close();
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

// empezamos con el menu contextual


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        final MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {

            case R.id.accion_eliminar:
                gestora.remove(info.position);
                getAdaptador().notifyDataSetChanged();
                return true;

            case R.id.accion_modificar:
                posicion = info.position;
                DialogoEdicionTelefono modificar = new DialogoEdicionTelefono();
                modificar.show(getFragmentManager(), "Modificar telefono");
                return true;
            default:
                return super.onContextItemSelected(item);

        }

    }

    // empezamos con el menu normal


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accion_ordenarPedir:
                this.gestora.ordenar_pedir();
                this.adaptador.notifyDataSetChanged();
                break;

            case R.id.accion_ordenarSaldo:
                this.gestora.ordenar_saldo();
                this.adaptador.notifyDataSetChanged();
                break;

            case R.id.accion_nuevaPersona:
                DialogoNuevaPersona dialogo = new DialogoNuevaPersona();
                dialogo.show(getFragmentManager(), "NUEVA PERSONA");
                break;

            case R.id.accion_modificarPrecioBebidas:
                DialogoModificrPrecioBebidas dialogoo = new DialogoModificrPrecioBebidas();
                dialogoo.show(getFragmentManager(), "MODIFICAR PRECIO");
                break;

            case R.id.accion_establecerCuotas:
                DialogoModificarCuota dialogooo = new DialogoModificarCuota();
                dialogooo.show(getFragmentManager(), "MODIFICAR CUOTA");

        }
        return true;
    }

   private void Shared() {
       SharedPreferences sharerd = this.getSharedPreferences("Precios", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = sharerd.edit();
       for (int i = 0; i < ClavesBebidas.values().length; i++) {
           if (!sharerd.contains(getResources().getString(ClavesBebidas.values()[i].getNombre()))) {
               editor.putFloat(getResources().getString(ClavesBebidas.values()[i].getNombre()), ClavesBebidas.values()[i].getPrecio());
           }
       }
       editor.commit();
   }



    @Override
    protected void onStart() {
        leerFichero();
        super.onStart();
    }

    @Override
    protected void onStop() {
        escribirFichero();
        super.onStop();
    }


    public void Beben(View view) {
        SparseBooleanArray seleccinados = lista.getCheckedItemPositions();
        if (seleccinados == null || seleccinados.size() == 0) {
            Toast.makeText(this, "No hay elementos seleccionados", Toast.LENGTH_SHORT).show();
        } else {

            for (int i = 0; i < seleccinados.size(); i++) {


                if (seleccinados.valueAt(i)) {
                    Persona person = gestora.get(seleccinados.keyAt(i));
                    float bebidaDeLaPersona =0;
                    SharedPreferences sharerd = this.getSharedPreferences("Precios", Context.MODE_PRIVATE);

                    //getResources().getString(ClavesBebidas.values()[i].getNombre())

                    ClavesBebidas clavesbebi = (ClavesBebidas) person.get(ClavesPersona.bebida.toString());
                    bebidaDeLaPersona = sharerd.getFloat(getResources().getString(clavesbebi.getNombre()),0.0f);


                    float saldoDeLaPersonaSeleccionada = (float) person.get(ClavesPersona.saldo.toString());



                    float total = saldoDeLaPersonaSeleccionada - bebidaDeLaPersona;

                    person.put(ClavesPersona.saldo.toString(), total);
                    adaptador.notifyDataSetChanged();
                }


            }


        }

    }


    //cuando pulsa el boton deberiamos añadir a el saldo de la persona la cuota que se ha añadido
    public void Ponen(View view) {

        SparseBooleanArray seleccinados = lista.getCheckedItemPositions();
        if (seleccinados == null || seleccinados.size() == 0) {
            Toast.makeText(this, "No hay elementos seleccionados", Toast.LENGTH_SHORT).show();
        } else {

            for (int i = 0; i < seleccinados.size(); i++) {


                if (seleccinados.valueAt(i)) {


                    Persona person = gestora.get(seleccinados.keyAt(i));
                    float saldoDeLaPersonaSeleccionada = (float) person.get(ClavesPersona.saldo.toString());
                    float cuota = gestora.getCuota();

                    float total = saldoDeLaPersonaSeleccionada + cuota;

                    person.put(ClavesPersona.saldo.toString(), total);


                }

                adaptador.notifyDataSetChanged();

            }

        }
    }
}
