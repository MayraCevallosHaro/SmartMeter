package com.example.smartmeter.Modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReporteConsumo {
    private String dispositivo;
    private String fecha;
    private String  consumo;

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public ReporteConsumo(JSONObject a) throws JSONException {
        dispositivo= a.getString("dispositivo").toString();
        fecha = a.getString("fecha").toString();
        consumo = a.getString("consumo").toString();


    }
    public static ArrayList<ReporteConsumo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<ReporteConsumo> dispo = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            dispo.add(new ReporteConsumo(datos.getJSONObject(i)));
        }
        return dispo;

    }
}
