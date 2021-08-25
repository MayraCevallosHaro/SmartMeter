package com.example.smartmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuAdmi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admi);
    }

    public void consumo(View view)
    {
        Intent intent = new Intent(this, Consumo.class);
        startActivity(intent);
        this.finish();
    }
    public void datos(View view)
    {
        Intent intent = new Intent(this, MisDatos.class);
        startActivity(intent);
        this.finish();
    }
}