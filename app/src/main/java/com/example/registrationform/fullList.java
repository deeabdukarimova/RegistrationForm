package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class fullList extends AppCompatActivity {
    /*Initialize views*/
    private TextView lastName,Name,Email,Country,Gender;
    private RecyclerView studentsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_list);

        /*Activity within the application which will be shown if the user wishes
         * to see the list of all registered applicants*/
        studentsRecView=findViewById(R.id.studentsRecView);



    }
}