package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FeedbckSuccefully extends AppCompatActivity {
    TextView Feedabck_detail;
    public void onBackPressed()
    {
         startActivity(new Intent(FeedbckSuccefully.this,UserDashboardPage.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbck_succefully);

        // create the get Intent object
        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String str = intent.getStringExtra("message_key");
        // display the string into textView
//        complaintDetail.setText(str);
    }
}