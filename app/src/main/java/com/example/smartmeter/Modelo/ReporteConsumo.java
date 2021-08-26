package com.example.smartmeter.Modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReporteConsumo {
    private String Usuario;
    private String mac;
    private String  Observacion;
    private String  fecha;
    private String  medida;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public ReporteConsumo(JSONObject a) throws JSONException {
        Usuario= a.getString("Usuario").toString();
        mac = a.getString("MAC").toString();
        Observacion = a.getString("Observacion").toString();
        fecha = a.getString("fecha").toString();
        medida = a.getString("medida").toString();

    }
    public static ArrayList<ReporteConsumo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<ReporteConsumo> dispo = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            dispo.add(new ReporteConsumo(datos.getJSONObject(i)));
        }
        return dispo;

    }
}
