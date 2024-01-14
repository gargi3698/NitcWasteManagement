package com.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Welcome extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null) {
            Intent i=new Intent(Welcome.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        else {
            if(firebaseAuth.getCurrentUser().getEmail().equals("admin@nitc.ac.in")) {
                Intent i=new Intent(Welcome.this, AdminDashboardPage.class);
                startActivity(i);
                finish();
            }
            else if(firebaseAuth.getCurrentUser().getEmail().equals("supervisor1@nitc.ac.in")) {
                Intent i = new Intent(Welcome.this, SupervisorDashboard.class);
                startActivity(i);
                finish();
            }
            else if(firebaseAuth.getCurrentUser().getEmail().equals("supervisor2@nitc.ac.in")) {
                Intent i = new Intent(Welcome.this, SupervisorDashboard.class);
                startActivity(i);
                finish();
            }
            else if(firebaseAuth.getCurrentUser().getEmail().equals("supervisor3@nitc.ac.in")) {
                Intent i = new Intent(Welcome.this, SupervisorDashboard.class);
                startActivity(i);
                finish();
            }
            else if(firebaseAuth.getCurrentUser().getEmail().equals("supervisor4@nitc.ac.in")) {
                Intent i = new Intent(Welcome.this, SupervisorDashboard.class);
                startActivity(i);
                finish();
            }
            else if(firebaseAuth.getCurrentUser().getEmail().equals("supervisor5@nitc.ac.in")) {
                Intent i = new Intent(Welcome.this, SupervisorDashboard.class);
                startActivity(i);
                finish();
            }
            else {
                Intent i=new Intent(Welcome.this, UserDashboardPage.class);
                startActivity(i);
                finish();
            }

        }
    }
}