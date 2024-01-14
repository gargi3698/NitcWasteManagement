package com.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostComplaintPage extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("UserComplaint");
    DatabaseReference reference2 = database.getReference("ComplaintSummary");

    TextView username;
    EditText comp_description;
    Spinner area;
    String [] zones={"Zone-A(Main Building)","Zone-B(Mega Boys Hostel)","Zone-C(LH & SOMS)","Zone-D(Chemistry dept)","Zone-E(Sac Building)"};
    EditText landmark;
    Button sendbtn;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user  = auth.getCurrentUser();
    private DatabaseReference ref;


    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PostComplaintPage.this,UserDashboardPage.class));
        finish();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_complaint_page);
        username=findViewById(R.id.username);
        comp_description = findViewById(R.id.writeincompliantbox);
        area = findViewById(R.id.complaintinarea);
        landmark= findViewById(R.id.complaintarealandmark);

        sendbtn = findViewById(R.id.send_complaint_button);
        ref= database.getReference();
        username.setText(user.getEmail());


        ArrayAdapter<String> adapter =new ArrayAdapter<String>(PostComplaintPage.this, android.R.layout.simple_spinner_item,zones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area.setAdapter(adapter);
        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value=parent.getItemAtPosition(position).toString();
                Toast.makeText(PostComplaintPage.this, zones[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sendbtn.setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View v) {

                String uname=user.getEmail();
                String complaint_des = comp_description.getText().toString();
                String complaint_area = area.getSelectedItem().toString();
                String complaint_land = landmark.getText().toString();
                String str = comp_description.getText().toString();
                String area=comp_description.getText().toString();


                if (complaint_des.isEmpty() && complaint_area.isEmpty())
                {
                    Toast.makeText(PostComplaintPage.this, "Please enter the Complaint Description and Area", Toast.LENGTH_SHORT).show();
                }
                else if (complaint_des.isEmpty())
                {
                    Toast.makeText(PostComplaintPage.this, "Please enter the Complaint Description", Toast.LENGTH_SHORT).show();

                }
                else if (complaint_area.isEmpty())
                {
                    Toast.makeText(PostComplaintPage.this, "Please enter a Area", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // Create the Intent object of this class Context() to Second_activity class
                    Intent intent = new Intent(getApplicationContext(), ComplaintRegisterSuccessfully.class);

                    // now by putExtra method put the value in key, value pair key is
                    // message_key by this key we will receive the value, and put the string
                    intent.putExtra("message_key", str);
                    intent.putExtra("area",area);
                    // start the Intent
                    startActivity(intent);
                    complaint_by_userFirebase(uname,complaint_des,complaint_area,complaint_land);
                    Toast.makeText(PostComplaintPage.this, "Your complaint registered successfully", Toast.LENGTH_SHORT).show();


                }
            }
        });



    }
    private void complaint_by_userFirebase(String uname,String complaint_des, String complaint_area, String complaint_land)
    {
        String id=reference.push().getKey().toString();
        UserComplaint usercomplaint= new UserComplaint(uname,complaint_des,complaint_area,complaint_land,"WAITING",id,"NONE","NA");
//        reference.child("UserComplaint").


        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long total = (long) snapshot.child("totalCC").getValue();

                reference2.child("totalCC").setValue(++total);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.child(id).setValue(usercomplaint).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(PostComplaintPage.this, "Done", Toast.LENGTH_SHORT).show();
                }
            }
        });
        };

}