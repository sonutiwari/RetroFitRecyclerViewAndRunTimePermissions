package in.co.ikai.retrofitrecyclerviewandruntimepermissions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import in.co.ikai.retrofitrecyclerviewandruntimepermissions.adapters.FlagDataAdapter;
import in.co.ikai.retrofitrecyclerviewandruntimepermissions.dataModel.JSONResponse;
import in.co.ikai.retrofitrecyclerviewandruntimepermissions.dataModel.NationalFlag;
import in.co.ikai.retrofitrecyclerviewandruntimepermissions.networking.RequestInterface;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements FlagDataAdapter.OnCardClickListener {

    public static final String BASE_URL
            = "http://www.androidbegin.com/";
    private RecyclerView mRecyclerView;

    private CompositeDisposable mCompositeDisposable;

    private FlagDataAdapter mAdapter;

    private ArrayList<NationalFlag> mAndroidArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCompositeDisposable = new CompositeDisposable();
        initRecyclerView();
        loadJSON();
    }

    private void initRecyclerView() {

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(getApplicationContext(), 3);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void loadJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Call<JSONResponse> call = requestInterface.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                Log.e("Sonu", "onResponse: " + jsonResponse.toString());
                mAndroidArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getFlags()));
                mAdapter = new FlagDataAdapter(mAndroidArrayList
                        , MainActivity.this, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.toString()
                        , Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    @Override
    public void imageClicked(String url) {
        Intent intent = new Intent(this, ImageFullScreenActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
