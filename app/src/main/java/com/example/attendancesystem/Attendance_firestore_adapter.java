package com.example.attendancesystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Attendance_firestore_adapter extends FirestoreRecyclerAdapter<student, Attendance_firestore_adapter.view_holder> {


    public Attendance_firestore_adapter(@NonNull FirestoreRecyclerOptions<student> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull Attendance_firestore_adapter.view_holder holder, int position, @NonNull student model) {
        holder.studentRoll.setText(model.getNUM()+ "");
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_single,parent,false);
        return new view_holder(view);
    }


    //view holder

    public class view_holder extends RecyclerView.ViewHolder{

        TextView studentRoll;


        public view_holder(@NonNull View itemView) {
            super(itemView);

            studentRoll = itemView.findViewById(R.id.student_roll);

        }
    }
}
