package com.burhanudin.menampilkanlistapi.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.burhanudin.menampilkanlistapi.R;
import com.burhanudin.menampilkanlistapi.model.Datum;

import java.util.List;

public class AplikasiAdapter extends BaseAdapter {

    private Activity activity;
    private List<Datum> data;

    public AplikasiAdapter(Activity activity, List<Datum> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Datum getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(activity);
            view = layoutInflater.inflate(R.layout.list_datum, null);
        }

        TextView tvNamaAplikasi = view.findViewById(R.id.tvNamaAplikasi);
        TextView tvVersi = view.findViewById(R.id.tvVersi);

        final Datum data = getItem(position);

        tvNamaAplikasi.setText(data.getName());
        tvVersi.setText(data.getVersion());

        return view;
    }
}
