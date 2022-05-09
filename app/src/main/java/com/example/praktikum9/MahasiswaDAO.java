package com.example.praktikum9;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MahasiswaDAO {
    private DatabaseReference databaseReference;

    public MahasiswaDAO() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Mahasiswa.class.getSimpleName());
    }

    public Task<Void> insert(Mahasiswa mahasiswa) {
        return databaseReference.push().setValue(mahasiswa);
    }

    public Task<Void> update(String key, Mahasiswa mahasiswa) {
        return databaseReference.child(key).setValue(mahasiswa);
    }

    public Task<Void> delete (String key) {
        return databaseReference.child(key).removeValue();
    }

    public Query get() {
        return databaseReference.orderByKey();
    }

    public Task<DataSnapshot> getByKey(String key) {
        return databaseReference.child(key).get();
    }
}
