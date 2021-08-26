package com.example.smartmeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Consumo extends AppCompatActivity {
    Button Enviar,btnWhatsap;
    EditText inputMensaje,inputNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo);
        Enviar = (Button) findViewById(R.id.btnEnviarSms);
        btnWhatsap = findViewById(R.id.btnWhatsap);
        inputMensaje =findViewById(R.id.inputMensaje);
        inputNumero = findViewById(R.id.inputNumero);


        if(ActivityCompat.checkSelfPermission(
                Consumo.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                Consumo.this,Manifest
                        .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Consumo.this,new String[]
                    { Manifest.permission.SEND_SMS,},1000);
        }

        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(inputMensaje.getText().toString(),null,inputNumero.getText().toString(),null,null);
                    Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });



        btnWhatsap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputNumero.getText().toString().isEmpty())
                {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, inputMensaje.getText().toString());
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                }else
                {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_VIEW);
                    String uri = "whatsapp://send?phone="+inputNumero.getText().toString()+"&text="+inputMensaje.getText().toString();
                    sendIntent.setData(Uri.parse(uri));
                    startActivity(sendIntent);

                }


            }
        });



    }
}