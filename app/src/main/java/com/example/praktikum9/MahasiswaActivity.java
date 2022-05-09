package com.example.praktikum9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MahasiswaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button addMahasiswaButton;
    private MahasiswaAdapter mahasiswaAdapter;
    private MahasiswaDAO mahasiswaDAO = new MahasiswaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        initComponent();
        initRecyclerView();
        loadData();

        addMahasiswaButton.setOnClickListener(view -> addMahasiswahandler());
    }

    private void initComponent() {
        recyclerView = findViewById(R.id.recyclerView);
        addMahasiswaButton = findViewById(R.id.btn_tambahMahasiswa);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mahasiswaAdapter = new MahasiswaAdapter(this, mahasiswaDAO);
        recyclerView.setAdapter(mahasiswaAdapter);
    }

    private void loadData() {
        mahasiswaDAO.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Mahasiswa> tampung = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Mahasiswa mahasiswa = data.getValue(Mahasiswa.class);
                    mahasiswa.setKey(data.getKey());
                    tampung.add(mahasiswa);
                }
                mahasiswaAdapter.setData(tampung);
                mahasiswaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addMahasiswahandler() {
        startActivity(new Intent(this, TambahMahasiswaActivity.class));
    }
}