package com.example.wastemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SupervisorLogin extends AppCompatActivity {
    private EditText emailid, pass_word;
    FirebaseAuth mAuth;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_login);
        emailid=findViewById(R.id.supervisoremail);
        pass_word=findViewById(R.id.password);
        signup=findViewById(R.id.signup);
        Button loginBtn = findViewById(R.id.loginBtn);
        //  Button btn_sign = findViewById(R.id.btn_signup);
        mAuth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SupervisorLogin.this, "Sucessfully logged in ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SupervisorLogin.this, SupervisorSignUpPage.class));
                finish();
            }
        });
        loginBtn.setOnClickListener(v -> {
            String email= emailid.getText().toString().trim();
            String password=pass_word.getText().toString().trim();
            if(email.isEmpty())
            {
                emailid.setError("Email is empty");
                emailid.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                emailid.setError("Enter the valid email");
                emailid.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                pass_word.setError("Password is empty");
                pass_word.requestFocus();
                return;
            }

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {

                    Toast.makeText(SupervisorLogin.this, "Sucessfully logged in ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SupervisorLogin.this, SupervisorDashboard.class));
                    finish();
                }
                else
                {
                    Toast.makeText(SupervisorLogin.this,
                            "Please Check Your login Credentials",
                            Toast.LENGTH_SHORT).show();
                }

            });
        });
        //loginBtn.setOnClickListener(v -> startActivity(new Intent(UserLoginPage.this,SignUpPage.class )));
    }

}