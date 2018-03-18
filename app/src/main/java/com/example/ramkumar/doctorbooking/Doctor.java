package com.example.ramkumar.doctorbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends AppCompatActivity {

    DatabaseReference root, registerPatient, index;
    private List<RowItemSelect> rowItemSelect;

    private ListView patientList;
    private String patientName, patientMobile, patientDesease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        root = FirebaseDatabase.getInstance().getReference();
        registerPatient = root.child("Patient");
        rowItemSelect = new ArrayList<>();

        registerPatient.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                patientName = (String) dataSnapshot.child("PatientName").getValue();
                patientMobile = (String) dataSnapshot.child("PatientMobile").getValue();
                patientDesease = (String) dataSnapshot.child("Desease").getValue();
                RowItemSelect item = new RowItemSelect(patientName, patientMobile, patientDesease);
                rowItemSelect.add(item);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                patientName = (String) dataSnapshot.child("PatientName").getValue();
                patientMobile = (String) dataSnapshot.child("PatientMobile").getValue();
                patientDesease = (String) dataSnapshot.child("Desease").getValue();
                RowItemSelect item = new RowItemSelect(patientName, patientMobile, patientDesease);
                rowItemSelect.add(item);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                patientName = (String) dataSnapshot.child("PatientName").getValue();
                patientMobile = (String) dataSnapshot.child("PatientMobile").getValue();
                patientDesease = (String) dataSnapshot.child("Desease").getValue();
                RowItemSelect item = new RowItemSelect(patientName, patientMobile, patientDesease);
                rowItemSelect.add(item);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
