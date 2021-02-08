package com.example.registrationform;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class StudentsRecViewAdapter {



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
