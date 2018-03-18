package com.example.ramkumar.doctorbooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRegisteration extends AppCompatActivity {

    DatabaseReference root, registerPatient, index;
    private List<RowItemSelect> rowItemSelect;

    private EditText patientName, patientPhno, patientEmail, patientPassword, patientDesease;
    private Button ptRegisteration;

    private String patientNameStr, patientPhnoStr, patientEmailStr, patientPasswordStr, patientDeseaseStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registeration);

        root = FirebaseDatabase.getInstance().getReference();
        registerPatient = root.child("Patient");
        rowItemSelect = new ArrayList<RowItemSelect>();

        patientName = (EditText) findViewById(R.id.patientName);
        patientPhno = (EditText) findViewById(R.id.patientPhoneNumber);
        patientEmail = (EditText) findViewById(R.id.patientEmail);
        patientPassword = (EditText) findViewById(R.id.patientPassword);
        patientDesease = (EditText) findViewById(R.id.desease);
        ptRegisteration = (Button) findViewById(R.id.registerPatient);

        registerPatient.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                patientPhnoStr = (String) dataSnapshot.child("PatientMobile").getValue();
                RowItemSelect itemSelect = new RowItemSelect(patientNameStr, patientPhnoStr, patientEmailStr, patientPasswordStr, patientDeseaseStr);
                rowItemSelect.add(itemSelect);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                patientPhnoStr = (String) dataSnapshot.child("PatientMobile").getValue();
                RowItemSelect itemSelect = new RowItemSelect(patientNameStr, patientPhnoStr, patientEmailStr, patientPasswordStr, patientDeseaseStr);
                rowItemSelect.add(itemSelect);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                patientPhnoStr = (String) dataSnapshot.child("PatientMobile").getValue();
                RowItemSelect itemSelect = new RowItemSelect(patientNameStr, patientPhnoStr, patientEmailStr, patientPasswordStr, patientDeseaseStr);
                rowItemSelect.add(itemSelect);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ptRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean status = false;

                patientNameStr = patientName.getText().toString();
                patientPhnoStr = patientPhno.getText().toString();
                patientEmailStr = patientEmail.getText().toString();
                patientPasswordStr = patientPassword.getText().toString();
                patientDeseaseStr = patientDesease.getText().toString();

                for (int i = 0; i < rowItemSelect.size(); i++) {
                    if (patientPhno.equals(rowItemSelect.get(i).getPatientPhno())) {
                        status = true;
                        Toast.makeText(PatientRegisteration.this, "Already Registered...", Toast.LENGTH_LONG).show();
                    }
                }

                if (!status) {
                    index = registerPatient.push();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("PatientName", patientNameStr);
                    map.put("PatientMobile", patientPhnoStr);
                    map.put("PatientEmail", patientEmailStr);
                    map.put("PatientPassword", patientPasswordStr);
                    map.put("Desease", patientDeseaseStr);
                    map.put("indexKey", index.getKey());
                    index.updateChildren(map);

                    Intent intent = new Intent(PatientRegisteration.this, MainActivity.class);
                    Toast.makeText(PatientRegisteration.this, "Registered Succesfully...", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
