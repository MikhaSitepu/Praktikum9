package com.example.praktikum9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateMahasiswaActivity extends AppCompatActivity {

    private TextView titleTextView;
    private EditText namaEditText, nimEditText;
    private Button submitButton;
    private MahasiswaDAO mahasiswaDAO = new MahasiswaDAO();
    private Mahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_form_mahasiswa);

        String key = getIntent().getStringExtra("KEY");
        getAnggota(key);

        submitButton.setOnClickListener(view -> updateHandler(key));
    }

    private void initComponent() {
        titleTextView = findViewById(R.id.tv_form);
        namaEditText = findViewById(R.id.et_nama);
        nimEditText = findViewById(R.id.et_nim);
        submitButton = findViewById(R.id.btn_submit);

        titleTextView.setText("Form Tambah Anggota");
    }

    private void getAnggota(String key) {
        mahasiswaDAO.getByKey(key).addOnCompleteListener(task -> {
            if (!task.isSuccessful())
                return;
            mahasiswa = task.getResult().getValue(Mahasiswa.class);
            namaEditText.setText(mahasiswa.getNama());
            nimEditText.setText(mahasiswa.getNim());
        });
    }

    private void updateHandler(String key) {
        String nama = namaEditText.getText().toString();
        String nim = nimEditText.getText().toString();

        mahasiswa.setNama(nama);
        mahasiswa.setNim(nim);

        mahasiswaDAO.update(key, mahasiswa).addOnSuccessListener(res -> {
            Toast.makeText(
                    this,
                    "Berhasil mengubah mahasiswa!",
                    Toast.LENGTH_LONG
            ).show();
            startActivity(new Intent(this, MainActivity.class));
        }).addOnFailureListener(error -> Toast.makeText(
                this,
                "Gagal mengubah mahasiswa: " + error.getLocalizedMessage(),
                Toast.LENGTH_LONG
        ).show());
    }
}