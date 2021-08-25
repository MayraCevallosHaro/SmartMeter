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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartmeter.Modelo.Dispositivo;
import com.example.smartmeter.WSSoap.SOAPWork;
import com.example.smartmeter.WebServices.Asynchtask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import Adapter.AdapterDisp;


public class Dispositivos extends AppCompatActivity implements Asynchtask{
    String id;
    EditText inputMac;
    EditText inputObservacion;
    TextView textView19;

    ArrayList<String> paises;
    ListView lstDiaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);
        lstDiaps = (ListView)findViewById(R.id.listlistad);
        inputMac = findViewById(R.id.inputMac);
        inputObservacion = findViewById(R.id.inputObservacion);

        SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
        id = prefs.getString("id", "");
        loadDevice();

    }

    public void RegisterDevice(View view)
    {
        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "INSERT INTO public.dispositivo(\n" +
                    " id_usuario, \"MAC\", \"Observacion\") values('"+id+"','"+inputMac.getText()+"','"+inputObservacion.getText()+"')");

            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Smart_Meter_WS/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("procesar");
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
        Log.w("Resultado",result);
        if(result.equals("Error"))
        return;
        else if (!result.equals("OK"))
        {
              ArrayList<Dispositivo> lstDipso = new ArrayList<Dispositivo> ();
            JSONArray JSONlistaDisp =  new JSONArray(result);
            lstDipso = Dispositivo.JsonObjectsBuild(JSONlistaDisp);
            AdapterDisp adapatorUsuario = new AdapterDisp(Dispositivos.this, lstDipso);
            lstDiaps.setAdapter(adapatorUsuario);
        } else if (result.equals("OK"))
        {
            inputMac.setText("");
            inputObservacion.setText("");
            inputMac.clearFocus();
            inputObservacion.clearFocus();
            loadDevice();
        }

    }
    private void loadDevice()
    {

        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "Select * from dispositivo where id_usuario =" + id);

            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Smart_Meter_WS/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("ObtenerDisp");
            dd.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, "+e.toString(),Toast.LENGTH_LONG).show();
            Log.w("Error",e.toString());
        }


    }
}