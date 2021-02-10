package com.example.registrationform;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentsRecViewAdapter extends RecyclerView.Adapter<StudentsRecViewAdapter.ViewHolder_Student> {
    private ArrayList<StudentsContact> students=new ArrayList<>();
    private Context context;

    public StudentsRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder_Student onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*holds and returns the View*/
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.students_list,parent,false);
        ViewHolder_Student holder_student=new ViewHolder_Student(view);
        return holder_student;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Student holder, int position) {
        holder.txtLastName.setText(students.get(position).getLastName());
        holder.txtName.setText(students.get(position).getName());
        holder.txtEmail.setText(students.get(position).getEmail());
        holder.txtGender.setText(students.get(position).getGender());
        holder.txtCountry.setText(students.get(position).getCountry());

        /*if particular student is selected, display Toast message*/
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, students.get(position).getName()+" Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    /*inner class within the StudentsRecViewAdapter which will hold the views */
    public class ViewHolder_Student extends RecyclerView.ViewHolder{
        private TextView txtLastName,txtName,txtEmail,txtCountry,txtGender;
        private CardView parent;

        public ViewHolder_Student(@NonNull View itemView) {
            super(itemView);
            txtLastName=itemView.findViewById(R.id.txtLastName);
            txtName=itemView.findViewById(R.id.txtName);
            txtEmail=itemView.findViewById(R.id.txtEmail);
            txtGender=itemView.findViewById(R.id.txtGender);
            txtCountry=itemView.findViewById(R.id.txtCountry);
            parent=itemView.findViewById(R.id.parentList);
        }
    }
}
