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

public class AdminFeedbackPage extends AppCompatActivity {

    private RecyclerView  recyclerView;
    TextView viewfeedback;

    ArrayList<UserFeedbackclass> feedbacks = new ArrayList<UserFeedbackclass>();
    AdminFeedAdapter adminFeedAdapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminFeedbackPage.this,AdminDashboardPage.class));
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feedback_page);
        viewfeedback=findViewById(R.id.viewFeedback);
        recyclerView=findViewById(R.id.adminFeedbackRV);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("UserFeedback");
//        important line of code
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Log.d("snap",dataSnapshot.toString());
                    UserFeedbackclass newfeedback =dataSnapshot.getValue(UserFeedbackclass.class);
                    feedbacks.add(newfeedback);

                }
                adminFeedAdapter = new AdminFeedAdapter(getApplicationContext(),feedbacks);
                recyclerView.setAdapter(adminFeedAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}