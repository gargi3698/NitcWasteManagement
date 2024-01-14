package com.example.wastemanagement;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStaff extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    FirebaseAuth auth=FirebaseAuth.getInstance();

    EditText staff_name;
    EditText staff_phone;
    EditText staff_emailid;
    TextView staff_location;
    Spinner staff_status;

    Spinner staff_type;
    Button addbtn;

    String [] sStatus={"Active","Reserved"};
    String [] sType={"Driver","Worker"};

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddStaff.this,AdminManageStaff.class));
        finish();
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        staff_name = findViewById(R.id.staffname);
        staff_phone = findViewById(R.id.editphone);
        staff_emailid = findViewById(R.id.editemail);
        staff_type = findViewById(R.id.editlocation);
        staff_location=findViewById(R.id.SlocationE);
        staff_location.setText(auth.getCurrentUser().getEmail().toString());
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(AddStaff.this, android.R.layout.simple_spinner_item,sType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staff_type.setAdapter(adapter);
        staff_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value=parent.getItemAtPosition(position).toString();
                Toast.makeText(AddStaff.this, sType[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        addbtn = findViewById(R.id.addstaff);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sta_name = staff_name.getText().toString();
                String sta_phone = staff_phone.getText().toString();
                String sta_emailid = staff_emailid.getText().toString();
                String staffType = staff_type.getSelectedItem().toString();
                String status="Active";
                String location=auth.getCurrentUser().getEmail().toString();

                if (sta_name.isEmpty() && sta_phone.isEmpty() && sta_emailid.isEmpty() && staffType.isEmpty()) {
                    Toast.makeText(AddStaff.this, "Please enter the Name, phone number,email-Id and Location", Toast.LENGTH_SHORT).show();
                } else if (sta_name.isEmpty()) {
                    Toast.makeText(AddStaff.this, "Please enter the Staff Name", Toast.LENGTH_SHORT).show();

                } else if (sta_phone.isEmpty()) {
                    Toast.makeText(AddStaff.this, "Please enter phone Number", Toast.LENGTH_SHORT).show();
                } else if (sta_emailid.isEmpty()) {
                    Toast.makeText(AddStaff.this, "Please enter email Id", Toast.LENGTH_SHORT).show();
                } else if (staffType.isEmpty()) {
                    Toast.makeText(AddStaff.this, "Please enter Location", Toast.LENGTH_SHORT).show();
                } else if (sta_name.isEmpty() || sta_phone.isEmpty() || sta_emailid.isEmpty() || staffType.isEmpty()) {
                    Toast.makeText(AddStaff.this, "Please check your input", Toast.LENGTH_SHORT).show();
                } else {


                    Add_staff_to_Firebase(sta_name, sta_phone, sta_emailid, staffType,location,status);
                    Toast.makeText(AddStaff.this, "New Worker Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddStaff.this, AdminManageStaff.class));


                }
            }


            private void Add_staff_to_Firebase(String staff_name, String staff_phone, String staff_emailid, String staff_type,String staff_location,String staff_status) {
                String sid = reference.child("AddNewStaff").push().getKey().toString();
                AddNewStaff addnewstaff = new AddNewStaff(staff_name, staff_phone, staff_emailid, staff_type,staff_location,"Active",sid);
                reference.child("AddNewStaff").child(sid).setValue(addnewstaff);
            }
        });
    }
}
