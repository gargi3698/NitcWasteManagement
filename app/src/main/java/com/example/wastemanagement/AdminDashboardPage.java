package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class AdminDashboardPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard_page);

        Button adminApprove =findViewById(R.id.adminComplaint);
        Button adminAssign = findViewById(R.id.adminAssign);
        Button adminLogout = findViewById(R.id.adminLogout);
        Button feedbackBtn=findViewById(R.id.feedBtn);
        Button summary=findViewById(R.id.adminSummary);
        Button adminall=findViewById(R.id.adminComplaint2);
        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboardPage.this, "opening Feedback page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AdminDashboardPage.this, AdminFeedbackPage.class ));
                finish();
            }
        });
        adminApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminDashboardPage.this, "opening approval page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AdminDashboardPage.this, AdminComplaint.class ));
                finish();
            }
        });
        adminall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboardPage.this, "Opening All Complaint Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AdminDashboardPage.this, AdminViewComplaint.class ));
                finish();
            }
        });

        adminAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminDashboardPage.this, "Opening manage Staff page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AdminDashboardPage.this,AdminAssignComplaint.class ));
            }
        });
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardPage.this,ComplaintSummaryPage.class ));

            }
        });
        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminDashboardPage.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                startActivity(new Intent(AdminDashboardPage.this,MainActivity.class ));
            }
        });
    }

}