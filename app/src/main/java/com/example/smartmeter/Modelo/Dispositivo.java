package com.example.smartmeter.Modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Dispositivo {
    private String id_Dispositivo;
    private String id_usuario;
    private String mac;
    private String observacion;

    public String getId_Dispositivo() {
        return id_Dispositivo;
    }

    public void setId_Dispositivo(String id_Dispositivo) {
        this.id_Dispositivo = id_Dispositivo;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Dispositivo(JSONObject a) throws JSONException {
        mac = a.getString("mac").toString();

        observacion = a.getString("observacion").toString();


    }
    public static ArrayList<Dispositivo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Dispositivo> dispo = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            dispo.add(new Dispositivo(datos.getJSONObject(i)));
        }
        return dispo;

    }
}
