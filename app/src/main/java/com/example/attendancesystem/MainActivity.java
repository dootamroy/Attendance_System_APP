package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "msg";

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView firestoreList;
    private Attendance_firestore_adapter adapter;

    //FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firestoreList = findViewById(R.id.attendanceRecycler);

        Query query = firebaseFirestore
                .collection("Students").orderBy("NUM", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<student> options = new FirestoreRecyclerOptions.Builder<student>()
                .setQuery(query, new SnapshotParser<student>() {
                    @NonNull
                    @Override
                    public student parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        student std = snapshot.toObject(student.class);

                        String id = snapshot.getId();

                        return std;
                    }
                })
                .build();

        adapter = new Attendance_firestore_adapter(options);

        firestoreList.setHasFixedSize(true);
        firestoreList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        firestoreList.setAdapter(adapter);



    }


}