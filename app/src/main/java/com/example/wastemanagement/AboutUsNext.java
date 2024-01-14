package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutUsNext extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_next);

        Button NextBtn=findViewById(R.id.nextbtn);

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AboutUsNext.this, "Opening Contact Us", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AboutUsNext.this, AboutUs.class ));

            }
        });
    }
}