package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartmeter.Modelo.Dispositivo;
import com.example.smartmeter.Modelo.ReporteConsumo;
import com.example.smartmeter.R;

import java.util.ArrayList;

public class AdapterConsumo extends ArrayAdapter<ReporteConsumo> {
    public AdapterConsumo(Context context, ArrayList<ReporteConsumo> datos) {
        super(context, R.layout.li_lista_consumo, datos);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.li_lista_consumo, null);

        TextView lblMac = (TextView)item.findViewById(R.id.LblMac);
        TextView lblObs = (TextView)item.findViewById(R.id.LblObs);
        TextView Lblfecha = (TextView)item.findViewById(R.id.Lblfecha);
        TextView LblMedida = (TextView)item.findViewById(R.id.LblMedida);

        lblMac.setText(getItem(position).getMac());
        lblObs.setText(getItem(position).getObservacion());
        Lblfecha.setText(getItem(position).getFecha());
        LblMedida.setText(getItem(position).getMedida());
        return(item);
    }

}
