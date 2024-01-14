package com.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.UUID;

public class SignUpPage extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();


    EditText username,email,password,mobileno;
    Button signUp;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    FirebaseUser user = auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        username = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword2);
        mobileno = findViewById(R.id.editTextTextPassword);
        signUp = findViewById(R.id.button);

        signUp.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                String userEmailId = email.getText().toString();
                String userPassword = password.getText().toString();
                String mobileNo = mobileno.getText().toString();
                String userName = username.getText().toString();

                if (userEmailId.isEmpty() && userPassword.isEmpty() && mobileNo.isEmpty() && userName.isEmpty())
                {
                    Toast.makeText(SignUpPage.this, "Please enter the details", Toast.LENGTH_SHORT).show();
                }
                else if (userEmailId.isEmpty())
                {
                    Toast.makeText(SignUpPage.this, "Please enter a Email id", Toast.LENGTH_SHORT).show();

                }
                else if (userPassword.isEmpty())
                {
                    Toast.makeText(SignUpPage.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                }
                else if (mobileNo.isEmpty()) {
                    Toast.makeText(SignUpPage.this, "Please enter a Mobile no", Toast.LENGTH_SHORT).show();

                }
                else if (checkMobileNo(mobileNo)) {
                    Toast.makeText(SignUpPage.this, "Please enter a valid mobile no", Toast.LENGTH_SHORT).show();

                }
                else if (userName.isEmpty()) {
                    Toast.makeText(SignUpPage.this, "Please enter a username", Toast.LENGTH_SHORT).show();

                }
                else if (Patterns.EMAIL_ADDRESS.matcher(userEmailId).matches()) {
                    boolean check = checkNitcEmail(userEmailId);
                    if (check)
                       // System.out.println("inside check ");
                        signUpFirebase(userEmailId, userPassword);
                    else
                        Toast.makeText(SignUpPage.this, "Enter NITC email id", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(SignUpPage.this, "Please enter a valid email id", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean checkNitcEmail(String userEmailId)
    {
        String[] emailid = userEmailId.split("@");
        if(emailid[1].equals("nitc.ac.in"))
            return true;
        return false;

    }


    private Boolean checkMobileNo(String mobileNo)
    {
        if(mobileNo.length() != 10)
            return true;

        else
        {
            for(int i = 0; i < 10; i++)
            {
                if(!Character.isDigit(mobileNo.charAt(i)))
                {
                    return true;
                }
            }
        }
        return false;
    }
    private void signUpFirebase(String userEmailId, String userPassword) {

        auth.createUserWithEmailAndPassword(userEmailId,userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                            Toast.makeText(SignUpPage.this, "Your account is created successfully", Toast.LENGTH_LONG).show();
                            UserDetails user = new UserDetails(username.getText().toString(), email.getText().toString(),mobileno.getText().toString(),password.getText().toString());
                            reference.child("User").child(auth.getCurrentUser().getUid()).setValue(user);

                            FirebaseAuth.getInstance().signOut(); //we have to re login if not write this
                            Intent intent = new Intent(SignUpPage.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(SignUpPage.this, "This email id is already present", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(SignUpPage.this, "There is a problem, Please try after sometime", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            signUp.setClickable(true);
                        }
                    }
                });
    }





    }
