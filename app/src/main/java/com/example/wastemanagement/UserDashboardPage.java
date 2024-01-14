package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class UserDashboardPage extends AppCompatActivity {

    Button lodgeCBtn,viewCBtn,viewSBtn,viewProfile,feedbackBtn,logout;

    private ImageView imageview;

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserDashboardPage.this,MainActivity.class));
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard_page);
        imageview=findViewById(R.id.userDashPic);
        lodgeCBtn=findViewById(R.id.lodgeCbtn);
        viewCBtn=findViewById(R.id.viewCBtn);
        viewSBtn=findViewById(R.id.viewSBtn);
        viewProfile=findViewById(R.id.myProfile);
        feedbackBtn=findViewById(R.id.feedback);
        logout=findViewById(R.id.userlogout);

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDashboardPage.this, "profile page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserDashboardPage.this, UserProfilePage.class));

            }
        });
        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserDashboardPage.this, "Give Your Feedback here!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserDashboardPage.this, UserFeedback.class));
            }
        });
        lodgeCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDashboardPage.this, "post your complain here!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserDashboardPage.this, PostComplaintPage.class));
                finish();
            }
        });
        viewCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDashboardPage.this, "See your complain here!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserDashboardPage.this,UserViewComplaint.class));
                finish();
            }
        });
        viewSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDashboardPage.this, "See your complain here!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserDashboardPage.this,UserViewStatus.class));
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDashboardPage.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                startActivity(new Intent(UserDashboardPage.this,MainActivity.class ));
            }
        });


    }
}