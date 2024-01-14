package com.example.wastemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SupervisorDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_dashboard);
        Button sAttendance =findViewById(R.id.sAttendance);
        Button manageStaff = findViewById(R.id.managestaff);
        Button supervisorLogout = findViewById(R.id.supervisorlogout);
        Button sFeed=findViewById(R.id.sFeed);
        Button sSolved=findViewById(R.id.sSolStatus);


        sAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SupervisorDashboard.this, "opening Complaint page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SupervisorDashboard.this,SupervisorAttendance.class ));
                finish();
            }
        });

        manageStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SupervisorDashboard.this, "Opening manage Staff page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SupervisorDashboard.this,AdminManageStaff.class ));
                finish();
            }
        });
        supervisorLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                finish();
                Toast.makeText(SupervisorDashboard.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SupervisorDashboard.this,MainActivity.class ));
                finish();
            }
        });

        sFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SupervisorDashboard.this, "Give Your Feedback here!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SupervisorDashboard.this, UserFeedback.class));
                finish();
            }
        });
        sSolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SupervisorDashboard.this, "Update Work status here!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SupervisorDashboard.this, SupervisorViewComplaint.class));
                finish();
            }
        });


    }
}