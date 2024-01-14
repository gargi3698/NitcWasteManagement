package com.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class UserProfilePage extends AppCompatActivity {

    private TextView usernametext,emailtext,mobilenotext;
    private Button logout;
    private ImageView imageavatar,personpic,mobilepic,emailpic;
    private String email;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user  = auth.getCurrentUser();


    private FirebaseDatabase database;

    private DatabaseReference reference;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");


        usernametext = findViewById(R.id.signupusername);
        emailtext = findViewById(R.id.signupuseremailid);
        mobilenotext = findViewById(R.id.signupusermobileno);
        imageavatar = findViewById(R.id.imageView3);
        //personpic = findViewById(R.id.userprofilepic);
//        mobilepic = findViewById(R.id.userprofilmobilepic);
      //  emailpic = findViewById(R.id.userprofilemailpic);
        logout = findViewById(R.id.logoutProfile);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        reference.child("User").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usernametext.setText(snapshot.child("username").getValue().toString());
                emailtext.setText(snapshot.child("email").getValue().toString());
                mobilenotext.setText(snapshot.child("phone").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent (UserProfilePage.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

    }
}