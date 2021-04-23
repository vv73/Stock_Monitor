package com.example.stockmonitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*1*/ Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://finnhub.io/")
                .build();
        /*2*/ FinnhubService service = retrofit.create(FinnhubService.class);
        /*3*/ Call<List<Symbol>> symbolsListCall =
                service.listSymbols( "v1", getString(R.string.finHubToken), "US");
        RecyclerView recyclerView = findViewById(R.id.stockRecyclerView);
        recyclerView.setLayoutManager(
                new GridLayoutManager(MainActivity.this, 3));
        MyAdapter adapter = new MyAdapter(new ArrayList<Symbol>());
        recyclerView.setAdapter(adapter);

        symbolsListCall.enqueue(new Callback<List<Symbol>>() {
            @Override
            public void onResponse(Call<List<Symbol>> call, Response<List<Symbol>> response) {
                List<Symbol> symbols = response.body();
                adapter.updateStocks(symbols);
            }

            @Override
            public void onFailure(Call<List<Symbol>> call, Throwable t) {
                //((TextView)findViewById(R.id.textview1)).setText(t.getLocalizedMessage());
            }
        });


    }
}
