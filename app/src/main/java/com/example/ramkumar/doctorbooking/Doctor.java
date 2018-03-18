package com.example.ramkumar.doctorbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Button showPatient;
    private String patientName, patientMobile, patientDesease, special;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        root = FirebaseDatabase.getInstance().getReference();
        registerPatient = root.child("Patient");
        rowItemSelect = new ArrayList<>();

        patientList = (ListView) findViewById(R.id.doctorList);
        showPatient = (Button) findViewById(R.id.showPatient);

        Bundle bundle = getIntent().getExtras();
        special = bundle.getString("Special");
        System.out.println("Special " + special);
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

        showPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] items = new String[rowItemSelect.size()];
                int a = 0;
                System.out.println("RowItemSelect in Doctor Class = " + rowItemSelect);
                for(int i=0; i<rowItemSelect.size(); i++){
                    if(rowItemSelect.get(i).getPatientDesease().equals(special)){
                        items[a] = rowItemSelect.get(i).getPatientName();
                        a++;
                        System.out.println("rowItemSelect.get(i).getPatientName() " + rowItemSelect.get(i).getPatientName());
                    }
                }

                if(items != null){
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Doctor.this, R.layout.support_simple_spinner_dropdown_item, items);
                    patientList.setAdapter(adapter);
                }
            }
        });

    }
}
