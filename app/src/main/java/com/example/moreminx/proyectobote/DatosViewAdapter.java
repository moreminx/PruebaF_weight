package com.example.moreminx.proyectobote;

import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class DatosViewAdapter implements SimpleAdapter.ViewBinder {


    @Override
    public boolean setViewValue(View view, Object o, String s) {
        if (o instanceof ClavesBebidas ){

            ImageView vieww = (ImageView) view;
            ClavesBebidas bebidas = (ClavesBebidas) o;

            vieww.setImageResource(bebidas.getImagen());
            return true;
        }
        return false;
    }
}
