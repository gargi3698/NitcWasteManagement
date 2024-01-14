package com.example.wastemanagement;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SupervisorSignUpPage extends AppCompatActivity {

    FirebaseAuth authe = FirebaseAuth.getInstance();


    EditText supername,superemail,superpassword,supermobileno,teamdetails;
    Button submitsignup;
    Spinner super_location;

    String [] sZones={"Zone-A(Main Building)","Zone-B(Mega Boys Hostel)","Zone-C(LH & SOMS)","Zone-D(Chemistry dept)","Zone-E(Sac Building)"};

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    FirebaseUser supervisordetail = authe.getCurrentUser();

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SupervisorSignUpPage.this,OpenSupervisor.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_sign_up_page);
        supername = findViewById(R.id.supervisornamesignup);
        superemail = findViewById(R.id.supervisoremailid);
        superpassword = findViewById(R.id.supervisorpassword);
        supermobileno = findViewById(R.id.supervisorphonenosignup);
        super_location = findViewById(R.id.supervisorlocationid);
        submitsignup = findViewById(R.id.submitbuttonsignup);

        ArrayAdapter<String> adapter3 =new ArrayAdapter<String>(SupervisorSignUpPage.this, android.R.layout.simple_spinner_item,sZones);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        super_location.setAdapter(adapter3);
        super_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value=parent.getItemAtPosition(position).toString();
                Toast.makeText(SupervisorSignUpPage.this, sZones[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String supervisorEmailId = superemail.getText().toString();
                String supervisorPassword = superpassword.getText().toString();
                String supervisorMobileNo = supermobileno.getText().toString();
                String supervisorName = supername.getText().toString();
                String superlocation = super_location.getSelectedItem().toString();



                if (supervisorEmailId.isEmpty() && supervisorPassword.isEmpty() && supervisorMobileNo.isEmpty() && supervisorName.isEmpty() && superlocation.isEmpty())
                {
                    Toast.makeText(SupervisorSignUpPage.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else if (supervisorEmailId.isEmpty())
                {
                    Toast.makeText(SupervisorSignUpPage.this, "Please Enter  Email id", Toast.LENGTH_SHORT).show();

                }
                else if (supervisorPassword.isEmpty())
                {
                    Toast.makeText(SupervisorSignUpPage.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                }
                else if (superlocation.isEmpty())
                {
                    Toast.makeText(SupervisorSignUpPage.this, "Please enter a team members detalis", Toast.LENGTH_SHORT).show();
                }
                else if (supervisorMobileNo.isEmpty())
                {
                    Toast.makeText(SupervisorSignUpPage.this, "Please enter a Mobile no", Toast.LENGTH_SHORT).show();

                }
                else if (checksupervisorMobileNo(supervisorMobileNo)) {
                    Toast.makeText(SupervisorSignUpPage.this, "Please enter a valid mobile no", Toast.LENGTH_SHORT).show();

                }

                else if (supervisorName.isEmpty()) {
                    Toast.makeText(SupervisorSignUpPage.this, "Please enter a your name", Toast.LENGTH_SHORT).show();

                }
                else if (Patterns.EMAIL_ADDRESS.matcher(supervisorEmailId).matches()) {
                    boolean check = checksupervisorEmail(supervisorEmailId);
                    if (check)
                        // System.out.println("inside check ");
                        SignUpFirebase(supervisorEmailId, supervisorPassword);
                    else
                        Toast.makeText(SupervisorSignUpPage.this, "Enter NITC email id", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(SupervisorSignUpPage.this, "Please enter a valid email id", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private Boolean checksupervisorEmail(String supervisoremailid)
    {
        String[] emailid = supervisoremailid.split("@");
        if(emailid[1].equals("nitc.ac.in") || emailid[1].equals("gmail.com") || emailid[1].equals("yahoo.com"))
            return true;
        return false;

    }


    private Boolean checksupervisorMobileNo(String supervisorMobileNo)
    {
        if(supervisorMobileNo.length() != 10)
            return true;

        else
        {
            for(int i = 0; i < 10; i++)
            {
                if(!Character.isDigit(supervisorMobileNo.charAt(i)))
                {
                    return true;
                }
            }
        }
        return false;
    }
    private void SignUpFirebase(String supervisorEmailId, String supervisorPassword ) {

        authe.createUserWithEmailAndPassword(supervisorEmailId,supervisorPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SupervisorSignUpPage.this, "Your account is created successfully", Toast.LENGTH_LONG).show();
                    SupervisorDetails supervisorDetail = new SupervisorDetails(supername.getText().toString(), superemail.getText().toString(), supermobileno.getText().toString(), super_location.getSelectedItem().toString(),"ON DUTY");
                    reference.child("SupervisorDetails").child(authe.getCurrentUser().getUid()).setValue(supervisorDetail);

                    Intent intent = new Intent(SupervisorSignUpPage.this, OpenSupervisor.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(SupervisorSignUpPage.this, "This email id is already present", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(SupervisorSignUpPage.this, "There is a problem, Please try after sometime", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    submitsignup.setClickable(true);
                }
            }
        });
    }

}