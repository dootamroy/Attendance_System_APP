package com.example.attendancesystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class studentFirestoreAdapter extends FirestoreRecyclerAdapter<student, studentFirestoreAdapter.studentViewHolder> {


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
    }

    @NonNull
    @Override
    public studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new studentViewHolder(view);
    }

    public class studentViewHolder extends RecyclerView.ViewHolder{

        private final TextView num;

        public studentViewHolder(@NonNull View itemView) {
            super(itemView);

            num = itemView.findViewById(R.id.student_name);
        }
    }
}
