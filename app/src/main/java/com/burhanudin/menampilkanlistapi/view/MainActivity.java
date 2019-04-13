package com.burhanudin.menampilkanlistapi.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.burhanudin.menampilkanlistapi.R;
import com.burhanudin.menampilkanlistapi.model.Aplikasi;
import com.burhanudin.menampilkanlistapi.model.Datum;
import com.burhanudin.menampilkanlistapi.rest.ApiInterface;
import com.burhanudin.menampilkanlistapi.rest.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface mApiService;
    private ListView lvDatum;
    private List<Datum> datumList;
    private AplikasiAdapter aplikasiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiService = UtilsApi.getAPIService();
        initUI();

        initData();

    }

    private void initData() {
        Call<Aplikasi> aplikasiCall = mApiService.getAplikasi();
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setTitle("Sedang mengambil data dari API");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        aplikasiCall.enqueue(new Callback<Aplikasi>() {
            @Override
            public void onResponse(Call<Aplikasi> call, Response<Aplikasi> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    List<Datum> data = response.body().getData();
                    loadDataToUI(data);
                    Toast.makeText(MainActivity.this, "Sukses mengambil data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Aplikasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDataToUI(List<Datum> data) {
        datumList = data;
        aplikasiAdapter = new AplikasiAdapter(this, datumList);
        lvDatum.setAdapter(aplikasiAdapter);
    }


    private void initUI() {
        lvDatum = findViewById(R.id.lvDatum);
        datumList = new ArrayList<>();
        aplikasiAdapter = new AplikasiAdapter(this, datumList);
        lvDatum.setAdapter(aplikasiAdapter);
    }

}
