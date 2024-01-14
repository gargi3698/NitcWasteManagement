package com.example.wastemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OpenSupervisor extends AppCompatActivity {
    private Button supervisorLogin;
    private Button registerSupervisor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_supervisor);
        supervisorLogin = findViewById(R.id.supervisorlogin);
        registerSupervisor = findViewById(R.id.supervisorregister);


        supervisorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin();
            }
        });

        registerSupervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent4=new Intent(this,SupervisorRegister.class);
                // startActivity(intent4);
                openSupervisorregister();
            }
        });
    }

    private void openlogin() {
        Intent intent4 = new Intent(this, SupervisorLogin.class);
        startActivity(intent4);
    }

    private void  openSupervisorregister(){
        Intent intent4 = new Intent(this, SupervisorSignUpPage.class);
        startActivity(intent4);
    }
}