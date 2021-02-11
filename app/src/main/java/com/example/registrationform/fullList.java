package com.example.registrationform;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.ArrayLinkedVariables;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
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
    private TextView lastName,name,email,country,gender;
    private RecyclerView studentsRecView;

    @RequiresApi(api = Build.VERSION_CODES.N)
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
            for(int i=0;i<data.size();i++){
                String [] temp=data.get(i).split("\\s+");
                for(int j=0;j<temp.length;j+=5){
                    students.add(new StudentsContact(temp[j],temp[j+1],temp[j+2],temp[j+4],temp[j+3]));
                }
            }


        } catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        /*pass data to Recycler View*/
        StudentsRecViewAdapter adapter=new StudentsRecViewAdapter(this);
        adapter.setStudents(students);
        studentsRecView.setAdapter(adapter);
        studentsRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));



    }
}