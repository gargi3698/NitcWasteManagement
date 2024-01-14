package com.example.wastemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import javax.security.auth.login.LoginException;

//public class UserLoginPage extends AppCompatActivity {
//
//    FirebaseAuth auth;
//     EditText username;
//     EditText password;
//     Button loginBtn;
//    final TextView signupButton=findViewById(R.id.signup);
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.userloginpage);
//        username =findViewById(R.id.username);
//        password= findViewById(R.id.password);
//        loginBtn=findViewById(R.id.loginBtn);
//        auth=  FirebaseAuth.getInstance();
//
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://nitc-waste-management-4e478-default-rtdb.firebaseio.com/");
//
//
//        MaterialButton loginBn=(MaterialButton) findViewById(R.id.loginBtn);
//        //admin and
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            final String usernameTxt=username.getText().toString();
//            final String passwordTxt=password.getText().toString();
//                if(!Patterns.EMAIL_ADDRESS.matcher(usernameTxt).matches())
//                {
//                    username.setError("Enter the valid username");
//                    username.requestFocus();
//                    return;
//                }
//                if(password.length()<6)
//                {
//                    password.setError("Length of password is more than 6");
//                    password.requestFocus();
//                    return;
//                }
//                    if(usernameTxt.isEmpty() && passwordTxt.isEmpty())
//            {
//                Toast.makeText(UserLoginPage.this,"please enter your username and password",Toast.LENGTH_SHORT).show();
//            }
//            else if(usernameTxt.isEmpty())
//            {
//                Toast.makeText(UserLoginPage.this,"please enter your username ",Toast.LENGTH_SHORT).show();
//            }
//            else if(passwordTxt.isEmpty()) {
//                Toast.makeText(UserLoginPage.this, "please enter your username and password", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        //check if username is exist in firebase datbase
//                        if(snapshot.hasChild(usernameTxt)) {
//                            //usernaem is exist in firebase database
//                            //now get password of user from firebasse data and match it with user entered password
//
//                            final String getPassword = snapshot.child(usernameTxt).child("password").getValue(String.class);
//
//                            if (getPassword.equals(passwordTxt)) {
//                                System.out.println("hellio");
//                                Toast.makeText(UserLoginPage.this, "Sucessfully logged in ", Toast.LENGTH_SHORT).show();
//                                //open MAinActivity on success
//                                startActivity(new Intent(UserLoginPage.this, MainActivity.class));
//                                finish();
//                            }
//                            else {
//                                Toast.makeText(UserLoginPage.this, "wrong passwrod", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                            else{
//                                Toast.makeText(UserLoginPage.this,"Wrong username",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//
//            }
//        });
//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //openn register actitvty
//                //startActivity(new Intent(UserLoginPage.this, signupButton.class));
//            }
//        });
//    }
//}



import com.google.firebase.auth.FirebaseAuth;

public class UserLoginPage extends AppCompatActivity {
    private EditText user_name, pass_word;
    FirebaseAuth mAuth;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userloginpage);
        user_name=findViewById(R.id.username);
        pass_word=findViewById(R.id.password);
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserLoginPage.this, "Opening Registration Page ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserLoginPage.this, SignUpPage.class));
                finish();
            }
        });
        Button loginBtn = findViewById(R.id.loginBtn);
      //  Button btn_sign = findViewById(R.id.btn_signup);
        mAuth=FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(v -> {
            String email= user_name.getText().toString().trim();
            String password=pass_word.getText().toString().trim();
            if(email.isEmpty())
            {
                user_name.setError("Email is empty");
                user_name.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                user_name.setError("Enter the valid email");
                user_name.requestFocus();
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
                    if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                        Toast.makeText(UserLoginPage.this, "Sucessfully logged in ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UserLoginPage.this, UserDashboardPage.class));
                        finish();
                    }
                    else {
                        Toast.makeText(this, "please verify your email first", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }

                }
                else
                {
                    Toast.makeText(UserLoginPage.this,
                            "Please Check Your login Credentials",
                            Toast.LENGTH_SHORT).show();
                }

            });
        });
        //loginBtn.setOnClickListener(v -> startActivity(new Intent(UserLoginPage.this,SignUpPage.class )));
    }

}
