package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

    //private FirebaseFirestore firebaseFirestore;
    //private RecyclerView firestoreList;
    //private studentFirestoreAdapter adapter;
    private static final String TAG = "msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //firebaseFirestore = FirebaseFirestore.getInstance();
        //firestoreList = findViewById(R.id.listRecycler);

        //Query.
        /*
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

         */

    }

    public void showList(View view){

        Intent intent = new Intent(this, AttendanceRecyclerView.class);
        startActivity(intent);

    }

    public void temp(View view){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        /*
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });


        Toast.makeText(this, "Random data generated", Toast.LENGTH_SHORT).show();
        */

        //read data.
        db.collection("Students")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Toast.makeText(MainActivity.this, "docID: "+ document.getId() , Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}