package com.example.registrationform;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;



import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private Button PickImg, Register, allList;
    private Spinner countriesSpinner;
    private RelativeLayout parent;
    private EditText lastName, name, email, password, reenterPswd;
    private CheckBox iAgree;
    private RadioButton pswrdRadiobtn, male, female, other;
    private String Gender = "Male";
    private RadioGroup rdgGender;
    final String FILE_NAME = "students.txt";


    public class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch (view.getId()) {
                case R.id.edtPswdReenter:
                    pswrdRadiobtn.setChecked(true);
                    break;
                default:
                    break;
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PickImg = findViewById(R.id.btnPickImg);

        parent = findViewById(R.id.parentLayout);
        lastName = findViewById(R.id.edtLastName);
        name = findViewById(R.id.edtName);
        email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtPassword);
        reenterPswd = findViewById(R.id.edtPswdReenter);
        iAgree = findViewById(R.id.iAgreeBox);
        pswrdRadiobtn = findViewById(R.id.reEnterrb);
        male = findViewById(R.id.rbMale);
        female = findViewById(R.id.rbFemale);
        other = findViewById(R.id.rbOther);

        rdgGender = findViewById(R.id.rdgGender);

        rdgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbFemale:
                        Gender = "Female";
                        break;
                    case R.id.rbOther:
                        Gender = "Other";
                        break;
                    default:
                        Gender = "Male";
                        break;
                }
            }
        });


    }
}