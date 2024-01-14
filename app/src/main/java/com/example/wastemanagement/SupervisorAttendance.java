package com.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DirectAction;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SupervisorAttendance extends AppCompatActivity {

    Button attandancBtn;
    FirebaseAuth authe = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("SupervisorDetails");
    TextView heading;
    Spinner spinner;
    String [] attend= {"ON DUTY","ON LEAVE","SHIFT OVER"};
    final String[] spinnerdata = new String[1];
    String s=authe.getCurrentUser().getEmail().substring(0,11);
    ArrayList<SupervisorDetails> superd;

    public void onBackPressed() {
        Log.d("name",s);
        super.onBackPressed();
        startActivity(new Intent(SupervisorAttendance.this,SupervisorDashboard.class));
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_attendance);
        heading=findViewById(R.id.sAtt);
        spinner=findViewById(R.id.attendSpinner);
        attandancBtn=findViewById(R.id.attendanceBtn);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(SupervisorAttendance.this, android.R.layout.simple_spinner_item,attend);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              spinnerdata[0]=parent.getItemAtPosition(position).toString();
              Log.d("spinner",spinnerdata[0]);
                Toast.makeText(SupervisorAttendance.this, attend[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        attandancBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {





                        //cehck if email is registerd before
                        if (snapshot.hasChild(s)) {


                            reference.child(s).child("attendance").setValue(spinnerdata[0]);

                            Toast.makeText(getApplicationContext(), "Supervisor Attendence successfully", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}