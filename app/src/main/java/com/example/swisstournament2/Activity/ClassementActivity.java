package com.example.swisstournament2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.ClassementDTO;
import com.example.swisstournament2.R;
import com.example.swisstournament2.Retrofit.Apis.ClassementApi;
import com.example.swisstournament2.Retrofit.RetrofitService;
import com.example.swisstournament2.adapter.ClassementAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClassementActivity extends AppCompatActivity {
    private ClassementAdapter classementAdapter ;
    private RecyclerView recyclerView;
    private Retrofit retrofit = RetrofitService.getRetrofit();
    private ClassementApi classementApi = retrofit.create(ClassementApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classement_activity);
        initRecycleView();
        initClassementView();

    }

    private void initClassementView() {
        Button homBtn = findViewById(R.id.homeBtn);

        int id = getIntent().getExtras().getInt("IDTOURNOIS");
        Log.i("id tournois {}", id+"");
        classementApi.getClassementTournois(id)
                .enqueue(new Callback<ArrayList<ClassementDTO>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ClassementDTO>> call, Response<ArrayList<ClassementDTO>> response) {
                        ArrayList<ClassementDTO> classementTournoisArrayList= (ArrayList<ClassementDTO>) response.body();
                        loadClassement(classementTournoisArrayList);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ClassementDTO>> call, Throwable t) {

                    }
                });
        homBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassementActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.classementRecycleView);
        Log.i("initRecycleView ->","il y a eu un prblem"+recyclerView.toString());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadClassement(ArrayList<ClassementDTO> classement) {
        classementAdapter = new ClassementAdapter(classement);
        Log.d("DEBUG Adpter Classemnt", (classement.get(0).getIdPlayer())+" <- ID PLAYER");
        recyclerView.setAdapter(classementAdapter);
    }
}
