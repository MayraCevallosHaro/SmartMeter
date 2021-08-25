package com.example.smartmeter;

import static com.example.smartmeter.Clases.clsConexionBd.IP_SERVIDOR;
import static com.example.smartmeter.Clases.clsConexionBd.PUERTO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartmeter.Clases.clsConexionBd;
import com.example.smartmeter.WSSoap.SOAPWork;
import com.example.smartmeter.WebServices.Asynchtask;
import com.example.smartmeter.WebServices.Usuarios;

import org.json.JSONException;

import java.sql.PreparedStatement;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registro extends AppCompatActivity implements Asynchtask{
    EditText inputNombre;
    EditText inputApellidos;
    EditText inputCedul;
    EditText inputTipo ;
    EditText inputUserN;
    EditText inputClave;
    EditText inputCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inputNombre = findViewById(R.id.inputNombre);
        inputApellidos = findViewById(R.id.inputApellidos);
        inputCedul = findViewById(R.id.inputCedul);
        inputTipo = findViewById(R.id.inputTipo);
        inputUserN = findViewById(R.id.inputUserN);
        inputClave = findViewById(R.id.inputClave);
        inputCorreo = findViewById(R.id.inputCorreo);




    }

    public void register(View view)
    {

        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "(SELECT public.registar_usuario(\n" +
                    "\t'"+inputNombre.getText()+"', \n" +
                    "\t'"+inputApellidos.getText()+"', \n" +
                    "\t'"+inputCedul.getText()+"', \n" +
                    "\t'"+inputTipo.getText()+"', \n" +
                    "\t'"+inputUserN.getText()+"', \n" +
                    "\t'"+inputClave.getText()+"', \n" +
                    "\t'"+inputCorreo.getText()+"'\n" +
                    "))");
            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Smart_Meter_WS/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("Obtener");
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
        Log.w("Usuario Creado",result);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }











}