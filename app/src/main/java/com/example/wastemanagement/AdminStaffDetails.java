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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminStaffDetails extends AppCompatActivity {

    private RecyclerView  recyclerView;
    TextView viewStaff;
    //    Button approve,disapprove;
    ArrayList<AddNewStaff> staff = new ArrayList<AddNewStaff>();
    adminStaffAdapter adminStaffAdapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminStaffDetails.this,AdminManageStaff.class));
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_staff_details);
        viewStaff=findViewById(R.id.viewStaff);
        recyclerView=findViewById(R.id.adminStaffRV);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("AddNewStaff");
//        important line of code
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminStaffDetails.this));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Log.d("snap",dataSnapshot.toString());
                    dataSnapshot.getKey();
                    AddNewStaff newStaff =dataSnapshot.getValue(AddNewStaff.class);
                    staff.add(newStaff);

                }
                adminStaffAdapter = new adminStaffAdapter(AdminStaffDetails.this,staff);
                recyclerView.setAdapter(adminStaffAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}