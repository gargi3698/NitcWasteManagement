package com.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AdminAssignComplaint extends AppCompatActivity {

    private RecyclerView  recyclerView;
    TextView viewComplaint;
    //    Button approve,disapprove;
    ArrayList<UserComplaint> usercomplaint = new ArrayList<UserComplaint>();
    assignAdapter assignAdapter;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminAssignComplaint.this,AdminDashboardPage.class));
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_complaint);
        viewComplaint=findViewById(R.id.viewComplaint);
        recyclerView=findViewById(R.id.adminAssignRV);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("UserComplaint");
        DatabaseReference reference3=database.getReference("SupervisorDetails");
//        important line of code
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminAssignComplaint.this));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Log.d("snap",dataSnapshot.toString());
                    UserComplaint complaint =dataSnapshot.getValue(UserComplaint.class);
                    SupervisorDetails supervisors=dataSnapshot.getValue(SupervisorDetails.class);
                    DatabaseReference reference3=database.getReference("SupervisorDetails");
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();

                    if(Objects.equals(complaint.getStatus(), "APPROVED") && Objects.equals(complaint.getC_assign(), "NONE"))
                          usercomplaint.add(complaint);

                }
                assignAdapter = new assignAdapter(AdminAssignComplaint.this,usercomplaint);
                recyclerView.setAdapter(assignAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}