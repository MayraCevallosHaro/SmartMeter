package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartmeter.Modelo.Dispositivo;
import com.example.smartmeter.R;

import java.util.ArrayList;

public class AdapterDisp extends ArrayAdapter<Dispositivo> {
    public AdapterDisp(Context context, ArrayList<Dispositivo> datos) {
        super(context, R.layout.li_lista_disp, datos);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.li_lista_disp, null);

        TextView lblMac = (TextView)item.findViewById(R.id.LblMac);
        TextView lblObs = (TextView)item.findViewById(R.id.LblObs);




        //.error(R.drawable.imgnotfound)



        lblMac.setText(getItem(position).getMac());
        lblObs.setText(getItem(position).getObservacion());

        return(item);
    }
}
