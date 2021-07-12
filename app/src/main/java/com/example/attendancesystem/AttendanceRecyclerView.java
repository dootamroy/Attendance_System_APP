package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AttendanceRecyclerView extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView firestoreList;
    private studentFirestoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_recycler_view);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firestoreList = findViewById(R.id.listRecycler);

        //Query.

        Query query = firebaseFirestore.collection("dootam")
                .orderBy("num", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<student> options = new FirestoreRecyclerOptions.Builder<student>()
                .setQuery(query, new SnapshotParser<student>() {
                    @NonNull
                    //@org.jetbrains.annotations.NotNull
                    @Override
                    public student parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        student std = snapshot.toObject(student.class);
                        return std;
                    }
                })
                .build();

        adapter = new studentFirestoreAdapter(options);

        firestoreList.setHasFixedSize(true);
        firestoreList.setLayoutManager(new LinearLayoutManager(this));
        firestoreList.setAdapter(adapter);

    }
}