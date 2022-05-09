package com.example.praktikum9;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    private Activity context;
    private ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>();
    private MahasiswaDAO mahasiswaDAO;

    public MahasiswaAdapter(Activity context, MahasiswaDAO mahasiswaDAO) {
        this.context = context;
        this.mahasiswaDAO = mahasiswaDAO;
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View cardMahasiswa = layoutInflater.inflate(R.layout.card_mahasiswa,
                parent, false);

        return new MahasiswaViewHolder(cardMahasiswa, context, mahasiswaDAO);
    }

    public void setData (ArrayList<Mahasiswa> listMahasiswa) {
        this.listMahasiswa = listMahasiswa;
    }

    @Override
    public void onBindViewHolder(
            @NonNull MahasiswaViewHolder holder,
            int position
    ) {
        holder.bindView(listMahasiswa.get(position));
    }

    @Override
    public int getItemCount() {
        return listMahasiswa.size();
    }

    public static class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        private Activity context;
        public MahasiswaDAO mahasiswaDAO;
        private Button updateButton, deleteButton;
        private TextView namaTextView, nimTextView;

        public MahasiswaViewHolder(
                View view,
                Activity context,
                MahasiswaDAO mahasiswaDAO
        ) {
            super(view);
            this.context = context;
            this.mahasiswaDAO = mahasiswaDAO;
            initComponent(view);
        }

        private void initComponent(View view) {
            namaTextView = view.findViewById(R.id.namaTextView);
            nimTextView = view.findViewById(R.id.nimTextView);
            updateButton = view.findViewById(R.id.updateButton);
            deleteButton = view.findViewById(R.id.deleteButton);
        }

        public void bindView(Mahasiswa mahasiswa) {
            namaTextView.setText(mahasiswa.getNama());
            nimTextView.setText(mahasiswa.getNim());
            updateButton.setOnClickListener(view -> updateMahasiswa(mahasiswa));
            deleteButton.setOnClickListener(view -> deleteMahasiswa(mahasiswa));
        }

        private void updateMahasiswa(Mahasiswa mahasiswa) {
            Intent intent = new Intent(context, UpdateMahasiswaActivity.class);
            intent.putExtra("KEY", mahasiswa.getKey());
            context.startActivity(intent);
        }

        private void deleteMahasiswa(Mahasiswa mahasiswa) {
            mahasiswaDAO.delete(mahasiswa.getKey()).addOnSuccessListener(res ->
                    Toast.makeText(
                    context,
                    "Berhasil menghapus mahasiswa!",
                    Toast.LENGTH_LONG
            ).show()).addOnFailureListener(error -> Toast.makeText(
                    context,
                    "Gagal menghapus anggota: "+ error.getLocalizedMessage(),
                    Toast.LENGTH_LONG

            ).show());
        }
    }
}
