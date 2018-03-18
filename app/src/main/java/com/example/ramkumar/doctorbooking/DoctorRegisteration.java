package com.example.ramkumar.doctorbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRegisteration extends AppCompatActivity {

    DatabaseReference root, registerDoctor, index;
    private List<RowItemSelect> rowItemSelect;

    private EditText doctorName, doctorPhno, doctorEmail, doctorPassword, doctorSpecial;
    private MaterialBetterSpinner availableFrom, availableTo;
    private Button drRegisteration;

    private String doctorNameStr, doctorPhnoStr, doctorEmailStr, doctorPasswordStr, doctorSpecialStr, from, to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registeration);

        root = FirebaseDatabase.getInstance().getReference();
        registerDoctor = root.child("Doctor");
        rowItemSelect = new ArrayList<>();

        doctorName = (EditText) findViewById(R.id.doctorName);
        doctorPhno = (EditText) findViewById(R.id.doctorPhoneNumber);
        doctorEmail = (EditText) findViewById(R.id.doctorEmail);
        doctorPassword = (EditText) findViewById(R.id.doctorPassword);
        doctorSpecial = (EditText) findViewById(R.id.doctorSpeciality);
        availableFrom = (MaterialBetterSpinner) findViewById(R.id.doctorAvailabilityFrom);
        availableTo = (MaterialBetterSpinner) findViewById(R.id.doctorAvailabilityTo);
        drRegisteration = (Button) findViewById(R.id.registerDoctor);

        registerDoctor.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                doctorPhnoStr = (String) dataSnapshot.child("DoctorMobile").getValue();
                RowItemSelect itemSelect = new RowItemSelect(doctorNameStr, doctorPhnoStr, doctorEmailStr, doctorPasswordStr, doctorSpecialStr, from, to);
                rowItemSelect.add(itemSelect);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                doctorPhnoStr = (String) dataSnapshot.child("DoctorMobile").getValue();
                RowItemSelect itemSelect = new RowItemSelect(doctorNameStr, doctorPhnoStr, doctorEmailStr, doctorPasswordStr, doctorSpecialStr, from, to);
                rowItemSelect.add(itemSelect);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                doctorPhnoStr = (String) dataSnapshot.child("DoctorMobile").getValue();
                RowItemSelect itemSelect = new RowItemSelect(doctorNameStr, doctorPhnoStr, doctorEmailStr, doctorPasswordStr, doctorSpecialStr, from, to);
                rowItemSelect.add(itemSelect);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        drRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean status = false;

                doctorNameStr = doctorName.getText().toString();
                doctorPhnoStr = doctorPhno.getText().toString();
                doctorEmailStr = doctorEmail.getText().toString();
                doctorPasswordStr = doctorPassword.getText().toString();
                doctorSpecialStr = doctorSpecial.getText().toString();
                from = availableFrom.getText().toString();
                to = availableTo.getText().toString();

                for(int i=0; i<rowItemSelect.size(); i++){
                    if(doctorPhnoStr.equals(rowItemSelect.get(i).getDoctorPhno())) {
                        status = true;
                        Toast.makeText(DoctorRegisteration.this, "Already Registered...", Toast.LENGTH_LONG).show();

                    }
                }

                if(status == false){
                    index = registerDoctor.push();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("DrName", doctorNameStr);
                    map.put("DoctorMobile", doctorPhnoStr);
                    map.put("DrEmail", doctorEmailStr);
                    map.put("DrPassword", doctorPasswordStr);
                    map.put("Special", doctorSpecialStr);
                    map.put("From", from);
                    //map.put("To", to);
                    map.put("index", index.getKey());
                    index.updateChildren(map);

                    Intent intent = new Intent(DoctorRegisteration.this, MainActivity.class);
                    Toast.makeText(DoctorRegisteration.this, "Registered Successfullly...", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
