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

public class Patient extends AppCompatActivity {

    DatabaseReference root, registerDoctor, index;
    private List<RowItemSelect> rowItemSelect;

    private ListView doctorList;

    private String doctorName, doctorMobile, special, from, to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        root = FirebaseDatabase.getInstance().getReference();
        registerDoctor = root.child("Doctor");
        rowItemSelect = new ArrayList<>();

        doctorList = (ListView) findViewById(R.id.doctorList);

        registerDoctor.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                doctorName = (String) dataSnapshot.child("DrName").getValue();
                doctorMobile = (String) dataSnapshot.child("DoctorMobile").getValue();
                special = (String) dataSnapshot.child("Special").getValue();
                from = (String) dataSnapshot.child("From").getValue();
                to = (String) dataSnapshot.child("To").getValue();

                RowItemSelect item = new RowItemSelect(doctorName, doctorMobile, special, from, to, 1);
                rowItemSelect.add(item);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                doctorName = (String) dataSnapshot.child("DrName").getValue();
                doctorMobile = (String) dataSnapshot.child("DoctorMobile").getValue();
                special = (String) dataSnapshot.child("Special").getValue();
                from = (String) dataSnapshot.child("From").getValue();
                to = (String) dataSnapshot.child("To").getValue();

                RowItemSelect item = new RowItemSelect(doctorName, doctorMobile, special, from, to, 1);
                rowItemSelect.add(item);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                doctorName = (String) dataSnapshot.child("DrName").getValue();
                doctorMobile = (String) dataSnapshot.child("DoctorMobile").getValue();
                special = (String) dataSnapshot.child("Special").getValue();
                from = (String) dataSnapshot.child("From").getValue();
                to = (String) dataSnapshot.child("To").getValue();

                RowItemSelect item = new RowItemSelect(doctorName, doctorMobile, special, from, to, 1);
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
