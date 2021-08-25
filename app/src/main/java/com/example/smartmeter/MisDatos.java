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
import android.widget.Toast;

import com.example.smartmeter.WSSoap.SOAPWork;
import com.example.smartmeter.WebServices.Asynchtask;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.Map;

public class MisDatos extends AppCompatActivity implements Asynchtask{

    String id;
    EditText inputNombre;
    EditText inputApellidos;
    EditText inputCedul;
    EditText inputTipo ;
    EditText inputUserN;
    EditText inputCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos);
        inputNombre = findViewById(R.id.inputNombre);
        inputApellidos = findViewById(R.id.inputApellidos4);
        inputCedul = findViewById(R.id.inputCedul4);
        inputTipo = findViewById(R.id.inputTipo4);
        inputUserN = findViewById(R.id.inputUserN4);
        inputCorreo = findViewById(R.id.inputCorreo4);
        SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
         id = prefs.getString("id", "");
        loaddata();
    }

    public void consumo(View view)
    {
        Intent intent = new Intent(this, Consumo.class);
        startActivity(intent);
        this.finish();
    }
    public void menu(View view)
    {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        this.finish();
    }
    public void report(View view)
    {
        Intent intent = new Intent(this, report_power.class);
        startActivity(intent);
        this.finish();
    }
    private void loaddata()
    {
        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "" +
                    "SELECT  nombres, apellidos, cedula, tipo, usuario, correo\n" +
                    "\tFROM public.usuario where id_usuario = "+id);
            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Smart_Meter_WS/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("Obtener");
            dd.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, "+e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void update(View view)
    {
        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "UPDATE public.usuario\n" +
                    "\tSET nombres='"+inputNombre.getText()+"'," +
                    " apellidos='"+inputApellidos.getText()+"'," +
                    " cedula='"+inputCedul.getText()+"'," +
                    " tipo='"+inputTipo.getText()+"', " +
                    "usuario='"+inputUserN.getText()+"', " +
                    "correo='"+inputCorreo.getText()+"'\n" +
                    "\tWHERE id_usuario="+id);
            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Smart_Meter_WS/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("procesar");
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
        if(result.equals("OK")) return;
        try {
            String [] data = result.split("-");
            inputNombre.setText(data[0]);
            inputApellidos.setText(data[1]);
            inputCedul.setText(data[2]);
            inputTipo.setText(data[3]);
            inputUserN.setText(data[4]);
            inputCorreo.setText(data[5]);
        }
        catch (Exception e )
        {

        }


    }
}