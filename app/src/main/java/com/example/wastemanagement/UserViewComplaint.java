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

public class UserViewComplaint extends AppCompatActivity {

    private RecyclerView  recyclerView;
    TextView viewComplaint;
    //    Button approve,disapprove;
    ArrayList<UserComplaint> usercomplaint = new ArrayList<UserComplaint>();
    userViewAdapter userViewAdapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserViewComplaint.this,UserDashboardPage.class));
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_complaint);
        viewComplaint=findViewById(R.id.viewComplaint);
        recyclerView=findViewById(R.id.userComplaintRV);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("UserComplaint");
//        important line of code
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Log.d("snap",dataSnapshot.toString());
                    UserComplaint complaint =dataSnapshot.getValue(UserComplaint.class);
                    FirebaseAuth mAuth=FirebaseAuth.getInstance();
//
                    Log.d("email",mAuth.getCurrentUser().getEmail());
                    if(Objects.equals(complaint.getUname(), mAuth.getCurrentUser().getEmail()))
                          usercomplaint.add(complaint);

                }
                userViewAdapter = new userViewAdapter(getApplicationContext(),usercomplaint);
                recyclerView.setAdapter(userViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}