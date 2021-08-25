package com.example.smartmeter.WebServices;


import android.widget.Toast;

import org.json.JSONException;

public interface Asynchtask {
    /**
     * ESta funcion retorna los datos devueltos por el ws
     * @param result
     */

    void processFinish(String result) throws JSONException;

}
