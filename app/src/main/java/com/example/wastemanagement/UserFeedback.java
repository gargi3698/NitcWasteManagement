package com.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserFeedback extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("UserFeedback");


    EditText FeedbackDescription;
    TextView username;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user  = auth.getCurrentUser();
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);
        username = findViewById(R.id.fdbkusername);
        FeedbackDescription = findViewById(R.id.textfeedback);
        submitBtn = findViewById(R.id.submitfeedback);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
//        String currentDate = sdf.format(new Date());
//        Log.d("date",currentDate);
        username.setText(user.getEmail());
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String feedback_des = FeedbackDescription.getText().toString();
                String str = FeedbackDescription.getText().toString();

                if (feedback_des.isEmpty() && uname.isEmpty()) {
                    Toast.makeText(UserFeedback.this, "Please give your the Feedback", Toast.LENGTH_SHORT).show();
                } else if (feedback_des.isEmpty()) {
                    Toast.makeText(UserFeedback.this, "Please enter the feedback", Toast.LENGTH_SHORT).show();

                } else if (uname.isEmpty()) {
                    Toast.makeText(UserFeedback.this, "Please enter uour username", Toast.LENGTH_SHORT).show();
                } else {
                    // Create the Intent object of this class Context() to Second_activity class
                    Intent intent = new Intent(getApplicationContext(), FeedbckSuccefully.class);

                    // now by putExtra method put the value in key, value pair key is
                    // message_key by this key we will receive the value, and put the string
                    intent.putExtra("message_fdbk", str);
                    // start the Intent
                    startActivity(intent);
                    feedback_by_userFirebase(uname, feedback_des);
                    Toast.makeText(UserFeedback.this, "Your Feedback Given successfully", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
    private void feedback_by_userFirebase(String uname,String feedback_des)
    {
        String id=reference.push().getKey().toString();
        UserFeedbackclass userfeedbk= new UserFeedbackclass(uname,feedback_des,id);
//        reference.child("UserComplaint").


        reference.child(id).setValue(userfeedbk).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UserFeedback.this, "feedback given", Toast.LENGTH_SHORT).show();
                }
            }
        });
    };

}