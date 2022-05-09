package com.example.praktikum9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TambahMahasiswaActivity extends AppCompatActivity {

    private TextView titleTextView;
    private EditText namaEditText, nimEditText;
    private Button submitButton;
    private MahasiswaDAO mahasiswaDAO = new MahasiswaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_form_mahasiswa);

        initComponent();

        submitButton.setOnClickListener(view -> insertHandler());
    }

    private void initComponent() {
        titleTextView = findViewById(R.id.tv_form);
        namaEditText = findViewById(R.id.et_nama);
        nimEditText = findViewById(R.id.et_nim);
        submitButton = findViewById(R.id.btn_submit);

        titleTextView.setText("Form Tambah Anggota");
    }

    private void insertHandler() {
        String nama = namaEditText.getText().toString();
        String nim = nimEditText.getText().toString();
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        Mahasiswa mahasiswa = new Mahasiswa(nama, nim);

        mahasiswaDAO.insert(mahasiswa).addOnSuccessListener(success -> {
            Toast.makeText(this, "Berhasil menambah mahasiswa!",
                    Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        }).addOnFailureListener(error -> Toast.makeText(
                this,
                "Gagal menambahkan anggota: " + error.getLocalizedMessage(),
                Toast.LENGTH_LONG
        ).show());
    }
}