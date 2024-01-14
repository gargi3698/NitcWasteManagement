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

import java.util.ArrayList;
import java.util.Objects;

public class SupervisorViewComplaint extends AppCompatActivity {

    private RecyclerView  recyclerView;
    TextView viewComplaint;
    //    Button approve,disapprove;
    ArrayList<UserComplaint> usercomplaint = new ArrayList<UserComplaint>();
    superAdapter superAdapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SupervisorViewComplaint.this,SupervisorDashboard.class));
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_view_complaint);
        viewComplaint=findViewById(R.id.viewComplaint);
        recyclerView=findViewById(R.id.superComplaintRV);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("UserComplaint");
        DatabaseReference reference5 = database.getReference("SupervisorDetails");
//        important line of code
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Log.d("snap",dataSnapshot.toString());
                    UserComplaint complaint =dataSnapshot.getValue(UserComplaint.class);

                    FirebaseAuth mAuth=FirebaseAuth.getInstance();
                    //String assignTo=reference5.child(mAuth.getCurrentUser().getUid()).child("supervisorname").getRef().getKey().toString();
                    Log.d("email",mAuth.getCurrentUser().getEmail());
                    String s=mAuth.getCurrentUser().getEmail().toString();
                    Log.d("name",s.substring(0,11));
                  //  Log.d("assignto",assignTo);
                    Log.d("assignedto",complaint.getC_assign());
                    if(Objects.equals(complaint.getC_assign(), s.substring(0,11)) && (Objects.equals(complaint.getC_progress(),"NA")  || Objects.equals(complaint.getC_progress(),"IN PROGRESS")) )
                        usercomplaint.add(complaint);

                }
                superAdapter = new superAdapter(getApplicationContext(),usercomplaint);
                recyclerView.setAdapter(superAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}