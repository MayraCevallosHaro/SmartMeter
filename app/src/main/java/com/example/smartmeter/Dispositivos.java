package com.example.smartmeter;

import static com.example.smartmeter.Clases.clsConexionBd.IP_SERVIDOR;
import static com.example.smartmeter.Clases.clsConexionBd.PUERTO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartmeter.WSSoap.SOAPWork;
import com.example.smartmeter.WebServices.Asynchtask;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Dispositivos extends AppCompatActivity implements Asynchtask{
    String id;
    EditText inputMac;
    EditText inputObservacion;
    TextView textView19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);

        inputMac = findViewById(R.id.inputMac);
        inputObservacion = findViewById(R.id.inputObservacion);

        SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
        id = prefs.getString("id", "");





    }

    public void RegisterDevice(View view)
    {
        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "INSERT INTO public.consumo('"+id+"','"+inputMac.getText()+"','"+inputObservacion.getText()+"')");

            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Smart_Meter/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("InsertDispositivo");
            dd.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, "+e.toString(),Toast.LENGTH_LONG).show();
            Log.w("Error",e.toString());
        }
    }

    @Override
    public void processFinish(String result) throws JSONException {

        Log.w("Dispositivo Creado",result);
        Intent intent = new Intent(this, MenuAdmi.class);
        startActivity(intent);
        this.finish();
    }
}