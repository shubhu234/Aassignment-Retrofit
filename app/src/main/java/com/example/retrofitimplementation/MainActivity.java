package com.example.countriesretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofitimplementation.Api;
import com.example.retrofitimplementation.Country;
import com.example.retrofitimplementation.CountryAdapter;
import com.example.retrofitimplementation.R;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CountryAdapter mCountryAdapterRecycleView;
    private ArrayList<Country> countryArrayList=new ArrayList<Country>();
    private static final String COUNTRY_ARRAY_LIST="CountryNameList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        Paper.init(this);

        if (!Paper.book().contains(COUNTRY_ARRAY_LIST)) {
            retrofitDataFetch();
        } else {
            countryArrayList = Paper.book().read(COUNTRY_ARRAY_LIST);
            Toast.makeText(MainActivity.this, "Stored", Toast.LENGTH_LONG).show();

            mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            mCountryAdapterRecycleView = new CountryAdapter(countryArrayList);
            mRecyclerView.setAdapter(mCountryAdapterRecycleView);
        }
    }
    private void retrofitDataFetch(){
        Retrofit mRetrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=mRetrofit.create(Api.class);

        Call<ArrayList<Country>> call=api.getCountries();
        call.enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                countryArrayList=response.body();
                Paper.book().write(COUNTRY_ARRAY_LIST,countryArrayList);

                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                mCountryAdapterRecycleView = new CountryAdapter(countryArrayList);
                mRecyclerView.setAdapter(mCountryAdapterRecycleView);
                Toast.makeText(MainActivity.this,"Thank you",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Sorry ",Toast.LENGTH_LONG).show();
            }
        });
    }
}


