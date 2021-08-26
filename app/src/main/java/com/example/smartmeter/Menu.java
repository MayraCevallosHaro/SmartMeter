package com.example.smartmeter;

import static com.example.smartmeter.Clases.clsConexionBd.IP_SERVIDOR;
import static com.example.smartmeter.Clases.clsConexionBd.PUERTO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartmeter.WSSoap.SOAPWork;
import com.example.smartmeter.WebServices.Asynchtask;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Menu extends AppCompatActivity  implements Asynchtask{
   TextView lblUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lblUser = findViewById(R.id.textView3);
        SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
        String user = prefs.getString("user", "");
        lblUser.setText(user);
    }

    public void consumo(View view)
    {
        Intent intent = new Intent(this, report_power.class);
        startActivity(intent);
    }
    public void datos(View view)
    {
        Intent intent = new Intent(this, MisDatos.class);
        startActivity(intent);

    }
    public void report(View view)
    {
        Intent intent = new Intent(this, Consumo.class);
        startActivity(intent);
    }

    public void dispositivos(View view)
    {
        Intent intent = new Intent(this, Dispositivos.class);
        startActivity(intent);
    }

    public void cerrar(View view)
    {
        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "Borrar");
            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Smart_Meter/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("cerrar");
            dd.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, "+e.toString(),Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void processFinish(String result) throws JSONException {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}