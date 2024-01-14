package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TextView rekha=(TextView)  findViewById(R.id.rekhalinkedin);

        rekha.setMovementMethod(LinkMovementMethod.getInstance());

        TextView aman=(TextView)  findViewById(R.id.amanlinkedin);
        aman.setMovementMethod(LinkMovementMethod.getInstance());

        TextView gargi =(TextView)  findViewById(R.id.gargilinkedin);
        gargi.setMovementMethod(LinkMovementMethod.getInstance());
    }
}