package com.example.ramkumar.doctorbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button doctorLogin, patientLogin;
    private TextView doctorReg, patientReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doctorLogin = (Button) findViewById(R.id.doctorLogin);
        patientLogin = (Button) findViewById(R.id.patientLogin);
        doctorReg = (TextView) findViewById(R.id.doctorRegisteration);
        patientReg = (TextView) findViewById(R.id.patientRegisteration);

        doctorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.putExtra("choice", "Doctor");
                startActivity(intent);

            }
        });

        patientLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.putExtra("choice", "Patient");
                startActivity(intent);

            }
        });

        doctorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DoctorRegisteration.class);
                startActivity(intent);

            }
        });

        patientReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PatientRegisteration.class);
                startActivity(intent);

            }
        });

    }
}
