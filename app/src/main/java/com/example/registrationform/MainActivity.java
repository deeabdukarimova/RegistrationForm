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

/*Using third party library to display the list of countries*/
import jp.gr.java_conf.androtaku.countrylist.CountryList;


public class MainActivity extends AppCompatActivity {
    private Button PickImg, Register, allList;
    private Spinner countriesSpinner;
    private RelativeLayout parent;
    private EditText lastName,name,email,password,reenterPswd;
    private CheckBox iAgree;
    private RadioButton pswrdRadiobtn,male,female,other;
    private String Gender="Male";
    private String errorMessage="errorMessage";
    private RadioGroup rdgGender;
    final String FILE_NAME="students.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Initialize Views*/
        initialize();

        /*Set the Gender value based on the user's choice*/
        rdgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbFemale:
                        Gender="Female";
                        break;
                    case R.id.rbOther:
                        Gender="Other";
                        break;
                    default:
                        Gender="Male";
                        break;
                }
            }
        });

        /*Create List where the country names will be stored (third party library is used to get
        the names of the countries) and push the list to spinner using ArrayAdapter*/
        List<String> countryNames = CountryList.getCountryNames(this);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, countryNames
        );
        countriesSpinner.setAdapter(countryAdapter);

        /*When user clicks on the Img, Toast msg is shown.*/
        PickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pick Image is Selected", Toast.LENGTH_SHORT).show();
            }
        });
        /*The RadioBtn Reenter passwod changes to Checked when the user starts re-typing the password*/
        reenterPswd.addTextChangedListener(new GenericTextWatcher(reenterPswd));

        /*CheckBox "I Agree" is set to true if the user chooses to Agree and checks the CheckBox*/
        iAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    iAgree.setChecked(true);
                }else{
                    iAgree.setChecked(false);
                }
            }
        });

        /*Register applicant if all fields are filled correctly, or show the error message and
         * wait until they fill the form correctly.After registering, show Snackbar that the Applicant is
         * successfully registered, and give the user option to register another user*/
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFields()){
                   // showSnackbar();   show SnackBar if fields are filled and meet requirements
                    save(lastName.getText().toString(),name.getText().toString(),email.getText().toString(),Gender,countriesSpinner.getSelectedItem().toString());
                }else {
                    //alert(errorMessage);   else we will show alert message
                }
            }
        });


    }
    /*Initialize elements*/
    public void initialize(){
        countriesSpinner = findViewById(R.id.spinnerCountries);
        PickImg = findViewById(R.id.btnPickImg);
        Register = findViewById(R.id.Register);
        parent = findViewById(R.id.parentLayout);
        lastName=findViewById(R.id.edtLastName);
        name=findViewById(R.id.edtName);
        email=findViewById(R.id.edtEmail);
        password=findViewById(R.id.edtPassword);
        reenterPswd=findViewById(R.id.edtPswdReenter);
        iAgree=findViewById(R.id.iAgreeBox);
        pswrdRadiobtn=findViewById(R.id.reEnterrb);
        male=findViewById(R.id.rbMale);
        female=findViewById(R.id.rbFemale);
        other=findViewById(R.id.rbOther);
        allList=findViewById(R.id.allList);
        rdgGender=findViewById(R.id.rdgGender);
    }
    /*Create textwatcher which changes the Radiobutton for
     * when user starts Reentering password*/
    public class GenericTextWatcher implements TextWatcher{
        private View view;
        private GenericTextWatcher (View view){
            this.view=view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        public void afterTextChanged(Editable editable) {
            // String text=editable.toString();
            switch(view.getId()){
                case R.id.edtPswdReenter:
                    pswrdRadiobtn.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }

    /*Check if all fields are entered correctly*/
    public boolean checkFields(){
        if(lastName.getText().toString().trim().length()==0){
            errorMessage="Please enter your last name";
            return false;
        }else if(name.getText().toString().trim().length()==0){
            errorMessage="Please enter your name";
            return false;
        }else if(email.getText().toString().trim().length()==0){
            errorMessage="Please enter your email";
            return false;
        }else if(password.getText().toString().trim().length()==0){
            errorMessage="Please enter your password";
            return false;
        }else if(reenterPswd.getText().toString().trim().length()==0){
            errorMessage="Please reenter your password";
            return false;
        }else if(name.getText().toString().contains(" ")){
            errorMessage="Name field should not contain whitespace";
            return false;
        }else if(email.getText().toString().contains(" ")){
            errorMessage="Email field should not contain whitespace";
            return false;
        }else if(password.getText().toString().contains(" ")){
            errorMessage="Password field should not contain whitespace";
            return false;
        }else if(lastName.getText().toString().contains(" ")){
            errorMessage="Last name field should not contain whitespace";
            return false;
        }else if(!email.getText().toString().contains("@") ){
            errorMessage="Please make sure email is in the format ...@...";
            return false;
        }else if(!password.getText().toString().equals(reenterPswd.getText().toString())){
            errorMessage="Please make sure original password matches to the one you re-enter";
            return false;
        }else if(iAgree.isChecked()==false){
            errorMessage="Please read the terms and check 'I agree' checkbox";
            return false;
        }return true;
    }

    /*Save data to the internal folder*/
    public void save(String lastName,String name, String email, String Gender, String Country){
        String info=lastName+ " " + name+" " + email + " "  + " " + Gender + " " + Country +"\n";
        FileOutputStream fos = null;

        try {
            fos=openFileOutput(FILE_NAME,MODE_APPEND);
            fos.write(info.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}