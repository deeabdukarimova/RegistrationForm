package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.ArrayLinkedVariables;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

        /*Initiate ArrayList StudentsContact*/
        ArrayList<StudentsContact> students=new ArrayList<>();

        /*ArrayList "data" will hold the data. Open the file from internal
        * storage, read to buffer and add to ArrayList to be able to use for displaying
        * purposes further*/
        ArrayList<String> data=new ArrayList<>();
        try{
            FileInputStream fileInputStream=openFileInput("students.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();

            String lines;
            while((lines=bufferedReader.readLine())!=null){
                stringBuffer.append(lines+"\n");
                data.add(lines+"\n");
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }



    }
}