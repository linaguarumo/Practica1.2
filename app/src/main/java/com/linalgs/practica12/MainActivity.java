package com.linalgs.practica12;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String loggin,password,rpassword,mail,sexo,hobbies="",ciudad;
    private int dia,mes,año;
    private EditText eLoggin, pPassword, pRPassword, eMail;
    private RadioButton rMasculino, rFemenino;
    private CheckBox cCine, cDormir, cComer, cBailar;
    private Spinner sCiudades;
    private TextView tInfo;


    DatePicker simpleDataPicker;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eLoggin = (EditText) findViewById(R.id.eLoggin);
        pPassword = (EditText) findViewById(R.id.pPassword);
        pRPassword = (EditText) findViewById(R.id.pRPassword);
        eMail = (EditText) findViewById(R.id.eMail);
        rMasculino = (RadioButton) findViewById(R.id.rMasculino);
        rFemenino = (RadioButton) findViewById(R.id.rFemenino);
        cCine = (CheckBox) findViewById(R.id.cCine);
        cDormir = (CheckBox) findViewById(R.id.cDormir);
        cComer = (CheckBox) findViewById(R.id.cComer);
        cBailar = (CheckBox) findViewById(R.id.cBailar);
        sCiudades = (Spinner) findViewById(R.id.sCiudades);

        tInfo = (TextView) findViewById(R.id.tInfo);
        simpleDataPicker =(DatePicker) findViewById(R.id.simpleDataPicker);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCiudades.setAdapter(adapter);

        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public void Aceptar(View view) {

        loggin = eLoggin.getText().toString();
        password = pPassword.getText().toString();
        rpassword = pRPassword.getText().toString();
        mail = eMail.getText().toString();
        String day = "" + simpleDataPicker.getDayOfMonth();
        String month = "/" + (simpleDataPicker.getMonth() + 1);
        String year = "/"+simpleDataPicker.getYear();


        if (loggin.equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese el Loggin",Toast.LENGTH_SHORT).show();
        }
        else if (!(password.equals(rpassword))){
            Toast.makeText(getApplicationContext(),"Ingrese Password que considan", Toast.LENGTH_SHORT).show();
        }
        else if (mail.equals("")){
            Toast.makeText(getApplicationContext(),"Ingrese su Correo",Toast.LENGTH_SHORT).show();
        }
        else if (!(cCine.isChecked()|| cDormir.isChecked()||cComer.isChecked()||cBailar.isChecked())){
            Toast.makeText(getApplicationContext(),"Ingrese Hobbies",Toast.LENGTH_SHORT).show();
        }
        else{
            hobbies=""; //Evita que se acomulen los hobies si das muchas veces click en aceptar

            if (rMasculino.isChecked()) {
                sexo = "Masculino";
            } else {
                sexo = "Femenino";
            }
            if (cCine.isChecked()) {
                hobbies += "cine ";
            }
            if (cDormir.isChecked()) {
                hobbies += "dormir ";
            }
            if (cComer.isChecked()) {
                hobbies += "comer ";
            }
            if (cBailar.isChecked()) {
                hobbies += "bailar ";
            }
        }

        tInfo.setText("Loggin: " + loggin + " \nPassword: " + password +" \nCorreo: " + mail +
                " \nSexo: " + sexo + "\nFecha de Nacimiento"+day+month+year+" \nhobbies: " + hobbies+ " \nciudad: " + ciudad);

    }
}