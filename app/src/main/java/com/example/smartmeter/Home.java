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

public class Home extends AppCompatActivity implements Asynchtask{

   EditText inputUser;
    EditText inputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        inputUser = findViewById(R.id.inputUser);
        inputPassword = findViewById(R.id.inputPassword);
    }



    public void iniciarsesion(View view)
    {
        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "select comprobar_usuarioP('"+inputUser.getText()+"','"+inputPassword.getText()+"')");
            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Smart_Meter_WS/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("consultar");
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
// Aqui es donde validas eso ... en result te viene el resultado de la bd

        if (result.equals("Error"))
            Toast.makeText(getApplicationContext(), "Resultado: Usuario Incorrecto "+result, Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(getApplicationContext(), "Resultado: "+result, Toast.LENGTH_LONG).show();
            SharedPreferences prefs = getSharedPreferences("shared_login_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            String [] data = result.trim().split("-");
            editor.putString("user", data[0]);
            editor.putString("id", data[1]);
            editor.putString("tipo",data[2]);

            editor.commit();
            if(data[2].equals("Adm"))
            {
               Intent intent = new Intent(this, MenuAdmi.class);
               startActivity(intent);
               this.finish();

           }else{
                Intent intent = new Intent(this, Menu.class);
                startActivity(intent);
                this.finish(); }



        }

    }
    public void registro(View view)
    {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
        this.finish();
    }
}