package com.example.smartmeter;

import static com.example.smartmeter.Clases.clsConexionBd.IP_SERVIDOR;
import static com.example.smartmeter.Clases.clsConexionBd.PUERTO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.fonts.FontFamily;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.smartmeter.Modelo.Dispositivo;
import com.example.smartmeter.Modelo.ReporteConsumo;
import com.example.smartmeter.WSSoap.SOAPWork;
import com.example.smartmeter.WebServices.Asynchtask;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import Adapter.AdapterConsumo;
import Adapter.AdapterDisp;

public class report_power extends AppCompatActivity implements Asynchtask{
    String id;
    Button btnGenerar;
    String NOMBRE_DIRECTORIO = "MisPDFs";
    String NOMBRE_DOCUMENTO = "MiPDF.pdf";
    ListView lswResporte;
    JSONArray JSONlistaConsumo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_power);

        btnGenerar = findViewById(R.id.btnGenerar);
        lswResporte =findViewById(R.id.listlistad);


        SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
        id = prefs.getString("id", "");


        loadData();
        // Permisos
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                    1000);
        }

        // Genera el documento
        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearPDF();
                Toast.makeText(report_power.this, "SE CREO EL PDF", Toast.LENGTH_LONG).show();
            }
        });




    }
    public void crearPDF() {
        Document documento = new Document();

        try {
            File file = crearFichero(NOMBRE_DOCUMENTO);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());

            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);

            documento.open();
            documento.add(new Paragraph("Smart Meter \n\n"));
            documento.add(new Paragraph( "Consumo Diario\n\n"));

            // Insertamos una tabla
            PdfPTable tabla = new PdfPTable(3);
            for (int i = 0; i < JSONlistaConsumo.length(); i++) {
                JSONObject object = JSONlistaConsumo.getJSONObject(i);
                tabla.addCell(object.getString("dispositivo"));
                tabla.addCell(object.getString("fecha"));
                tabla.addCell(object.getString("consumo"));
            }
            documento.add(tabla);
            documento.close();
        } catch(DocumentException e) {
        } catch(IOException e) {
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public File crearFichero(String nombreFichero) {
        File ruta = getRuta();

        File fichero = null;
        if(ruta != null) {
            fichero = new File(ruta, nombreFichero);
        }

        return fichero;
    }


    public File getRuta() {
        File ruta = null;

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ruta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), NOMBRE_DIRECTORIO);

            if(ruta != null) {
                if(!ruta.mkdirs()) {
                    if(!ruta.exists()) {
                        return null;
                    }
                }
            }

        }
        return ruta;
    }

    private void loadData()
    {

        Map<String, String> map = new LinkedHashMap<>();
        try
        {
            map.put("sentencia", "select d.dispositivo, c.fecha, SUM(c.medida) as Consumo " +
                    "from consumo as c inner join dispositivo as d ON c.id_dispositivo = d.id_dispositivo " +
                    "where d.id_usuario =" + id +
                    "group by c.fecha, d.dispositivo");

            SOAPWork dd = new SOAPWork("http://"+IP_SERVIDOR+":"+PUERTO+"/Smart_Meter_WS/ws_Procesar?WSDL", map, this, (Asynchtask) this);
            dd.setMethod_name("ConsumoUsuario");
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
         JSONlistaConsumo =  new JSONArray(result);
        ArrayList<ReporteConsumo> lstrconsumo = new ArrayList<ReporteConsumo> ();

        lstrconsumo = ReporteConsumo.JsonObjectsBuild(JSONlistaConsumo);
        AdapterConsumo adapatorUsuario = new AdapterConsumo(report_power.this, lstrconsumo);
        lswResporte.setAdapter(adapatorUsuario);
    }
}