package com.example.attendancesystem;
/*
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
*/

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

public class AttendanceRecyclerView extends AppCompatActivity {

    studentFirestoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_recycler_view);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView firestoreList = findViewById(R.id.listRecycler);

        //Query.

        Query query = firebaseFirestore.collection("Students")
                .orderBy("NUM", Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<student> options = new FirestoreRecyclerOptions.Builder<student>()
                .setQuery(query, new SnapshotParser<student>() {
                    @NonNull
                    @Override
                    public student parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        student inv = snapshot.toObject(student.class);
                        String item_id = snapshot.getId();
                        //inv.setItem_id(item_id);
                        inv.setRoll(item_id);

                        return inv;
                    }
                })
                .build();



        adapter = new studentFirestoreAdapter(options);

        firestoreList.setHasFixedSize(true);
        firestoreList.setLayoutManager(new LinearLayoutManager(this));
        firestoreList.setAdapter(adapter);

        //Recycler view on click event listener.......

        adapter.setOnItemClickListener(new studentFirestoreAdapter.onItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String id = documentSnapshot.getId();  //doc id.

                //extract the particular values.
                student demo = documentSnapshot.toObject(student.class);
                assert demo != null;
                int NUM = demo.getNUM();

                Toast.makeText(AttendanceRecyclerView.this, "Roll: "+ id +" Attendance: " + NUM, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter.stopListening();

    }
}