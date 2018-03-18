package com.example.ramkumar.doctorbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    DatabaseReference root, doctor, patient, index;
    private List<RowItemSelect> rowItemSelect;

    private EditText mobileNo, password;
    private Button login;
    private String mobileNoStr, passwordStr, choice, special, desease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        root = FirebaseDatabase.getInstance().getReference();
        doctor = root.child("Doctor");
        patient = root.child("Patient");
        rowItemSelect = new ArrayList<>();

        mobileNo = (EditText) findViewById(R.id.mobileNo);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.signIn);

        Bundle bundle = getIntent().getExtras();
        choice = bundle.getString("choice");
        System.out.println("choice" + choice);
        if (choice.equals("Doctor")) {
            /*  Selected Login is Doctor    */
            doctor.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    mobileNoStr = (String) dataSnapshot.child("DoctorMobile").getValue();
                    passwordStr = (String) dataSnapshot.child("DrPassword").getValue();
                    special = (String) dataSnapshot.child("Special").getValue();
                    RowItemSelect item = new RowItemSelect(mobileNoStr, passwordStr, 1, special);
                    rowItemSelect.add(item);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    mobileNoStr = (String) dataSnapshot.child("DoctorMobile").getValue();
                    passwordStr = (String) dataSnapshot.child("DrPassword").getValue();
                    special = (String) dataSnapshot.child("Special").getValue();
                    RowItemSelect item = new RowItemSelect(mobileNoStr, passwordStr, 1, special);
                    rowItemSelect.add(item);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    mobileNoStr = (String) dataSnapshot.child("DoctorMobile").getValue();
                    passwordStr = (String) dataSnapshot.child("DrPassword").getValue();
                    special = (String) dataSnapshot.child("Special").getValue();
                    RowItemSelect item = new RowItemSelect(mobileNoStr, passwordStr, 1, special);
                    rowItemSelect.add(item);

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else if (choice.equals("Patient")) {
            /*  Selected Login is Patient   */
            patient.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    mobileNoStr = (String) dataSnapshot.child("PatientMobile").getValue();
                    passwordStr = (String) dataSnapshot.child("PatientPassword").getValue();
                    desease = (String) dataSnapshot.child("Desease").getValue();
                    RowItemSelect item = new RowItemSelect(mobileNoStr, passwordStr, 1.0f, desease);
                    rowItemSelect.add(item);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    mobileNoStr = (String) dataSnapshot.child("PatientMobile").getValue();
                    passwordStr = (String) dataSnapshot.child("PatientPassword").getValue();
                    RowItemSelect item = new RowItemSelect(mobileNoStr, passwordStr, 1.0f, desease);
                    rowItemSelect.add(item);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    mobileNoStr = (String) dataSnapshot.child("PatientMobile").getValue();
                    passwordStr = (String) dataSnapshot.child("PatientPassword").getValue();
                    RowItemSelect item = new RowItemSelect(mobileNoStr, passwordStr, 1.0f, desease);
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
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mobileNoStr = mobileNo.getText().toString();
                    passwordStr = password.getText().toString();
                    System.out.println("choice " + choice);
                    if (choice.equals("Doctor")) {
                        for (int i = 0; i < rowItemSelect.size(); i++) {
                            if (mobileNoStr.equals(rowItemSelect.get(i).getDoctorPhno())) {
                                Intent intent = new Intent(Login.this, Doctor.class);
                                intent.putExtra("Special", rowItemSelect.get(i).getDoctorSpecial());
                                startActivity(intent);
                                finish();
                            }
                       }
                    }
                if (choice.equals("Patient")) {
                        for (int i = 0; i < rowItemSelect.size(); i++) {
                            if (mobileNoStr.equals(rowItemSelect.get(i).getDoctorPhno())) {
                                Intent intent = new Intent();
                                //intent.putExtra("Special", rowItemSelect.get(i).getDoctorSpecial());
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                }

            });


    }
}
