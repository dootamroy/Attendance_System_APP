package com.example.attendancesystem;
/*
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
*/
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class studentFirestoreAdapter extends FirestoreRecyclerAdapter<student, studentFirestoreAdapter.studentViewHolder> {

    private onItemClickListener listener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public studentFirestoreAdapter(@NonNull FirestoreRecyclerOptions<student> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull  studentFirestoreAdapter.studentViewHolder holder, int position, @NonNull  student model) {

        holder.num.setText("Classes Attended: " + model.getNUM());
        holder.student_ID.setText("Roll No. " + model.getRoll());
    }

    @NonNull
    @Override
    public studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new studentViewHolder(view);
    }

    public class studentViewHolder extends RecyclerView.ViewHolder{

        private final TextView num;
        private final TextView student_ID;

        public studentViewHolder(@NonNull View itemView) {
            super(itemView);

            num = itemView.findViewById(R.id.student_attendance);
            student_ID = itemView.findViewById(R.id.student_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }

                    //To sent a click from the adapter class to the underling activity an interface needs to be declared!!
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(onItemClickListener listener){ this.listener = listener; }
}
