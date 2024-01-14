package com.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ComplaintSummaryPage extends AppCompatActivity {

    private TextView approvecom,disapprovecom,solvedcom,unsolvedcom,totalcom;

    private FirebaseDatabase database;

    private DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_summary_page);

        approvecom = findViewById(R.id.approvecomplaintno);
        disapprovecom = findViewById(R.id.disapprovecomplaintno);
        solvedcom = findViewById(R.id.solvedcomplaintno);
        unsolvedcom = findViewById(R.id.unsolvedcomplaintno);
        totalcom = findViewById(R.id.totalcoplaintno);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        reference.child("ComplaintSummary").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                approvecom.setText(snapshot.child("approveC").getValue().toString());
                disapprovecom.setText(snapshot.child("disapproveC").getValue().toString());
                solvedcom.setText(snapshot.child("solvedC").getValue().toString());
                unsolvedcom.setText(snapshot.child("unsolvedC").getValue().toString());
                totalcom.setText(snapshot.child("totalCC").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}