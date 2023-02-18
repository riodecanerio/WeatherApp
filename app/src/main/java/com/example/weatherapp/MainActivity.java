package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import Retrofit.ApiClient;
import Retrofit.ApiInterface;
import Retrofit.Example;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editTextSearch;
    private ImageView imageViewSearch;
    private TextView textViewTemp, textViewFeels, textViewNem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = findViewById(R.id.editTextSearch);
        imageViewSearch = findViewById(R.id.imageViewSearch);
        textViewTemp = findViewById(R.id.textViewTemp);
        textViewFeels = findViewById(R.id.textViewFeels);
        textViewNem = findViewById(R.id.textViewNem);

        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getWeatherData(editTextSearch.getText().toString().trim());

            }
        });


    }

    private void getWeatherData(String name){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                textViewTemp.setText("Temp: "+response.body().getMain().getTemp()+" C");
                textViewFeels.setText("Feels Like: "+response.body().getMain().getFeels_like());
                textViewNem.setText("Humidity: "+response.body().getMain().getHumidity());

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }

}