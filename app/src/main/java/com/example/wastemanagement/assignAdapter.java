package com.example.wastemanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class assignAdapter extends RecyclerView.Adapter<assignAdapter.assignViewHolder> {


    @NonNull

    Context context;
    ArrayList<UserComplaint> usercomplaint;
    ArrayList<SupervisorDetails> superDet;

    FirebaseAuth auth=FirebaseAuth.getInstance();


    public assignAdapter(@NonNull Context context, ArrayList<UserComplaint> usercomplaint) {
        this.context = context;
        this.usercomplaint = usercomplaint;
    }

    public assignAdapter.assignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_allassign_page,parent,false);

        return new assignViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull assignViewHolder holder, int position) {
        UserComplaint complaint=usercomplaint.get(position);
        holder.username.setText(complaint.getUname());
        holder.cDes.setText(complaint.getComplaint_des());
        holder.cArea.setText(complaint.getComplaint_area());
        holder.cLandmark.setText(complaint.getComplaint_land());
        holder.cStatus.setText(complaint.getStatus());
        holder.assignTo.setText(complaint.getC_assign());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference reference = database.getReference("UserComplaint");
        DatabaseReference reference2 = database.getReference("ComplaintSummary");
        DatabaseReference reference3 = database.getReference("UserComplaint");
        holder.assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.username.getContext())
                        .setContentHolder(new ViewHolder(R.layout.assign_staff_content))
                        .setExpanded(true,900)
                        .create();


                View view1=dialogPlus.getHolderView();

                Spinner spinner=view1.findViewById(R.id.sSelect);




                final String[] spinnerdata = new String[1];


                ArrayList<String> arr =new ArrayList<>();
//
//
//


                final FirebaseDatabase[] database = {FirebaseDatabase.getInstance()};
                DatabaseReference reference = database[0].getReference();

                reference.child("SupervisorDetails").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //System.out.println(snapshot.getKey());
                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            System.out.println("hello    "+snapshot.getKey());
                            if(snapshot1.child("attendance").getValue().toString().equals("ON DUTY") && (Objects.equals(complaint.getComplaint_area(),snapshot1.child("supervisorZone").getValue().toString()))) {
                                arr.add(snapshot1.child("supervisorname").getValue().toString());
                                String s1 = snapshot1.child("attendance").getValue().toString();
                                Log.d("attendance", s1);
                            }




                        }
                        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arr);//mod
                        spinner.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                /*new */

                String [] values={"supervisor1","supervisor2","supervisor3","supervisor4","supervisor5"};




                ArrayList<String> arrayList=new ArrayList<>(Arrays.asList(values));
                //charger

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        spinnerdata[0] =adapterView.getItemAtPosition(i).toString();
                        Toast.makeText(context, ""+ spinnerdata[0], Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                Button assign=view1.findViewById(R.id.comAssign);


                dialogPlus.show();
                assign.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        // System.out.println("update");
                        //for(int i = 0; i<arr.size(); i++)
                        //  System.out.println(arr.get(i));
                        Map<String ,Object> map=new HashMap<>() ;
                        map.put("c_id",complaint.getC_id());
                        map.put("complaint_area",complaint.getComplaint_area());
                        map.put("complaint_des",complaint.getComplaint_des());
                        map.put("complaint_land",complaint.getComplaint_land());
                        map.put("status",complaint.getStatus());
                        map.put("uname",complaint.getUname());

                        map.put("c_assign",spinnerdata[0]);
                        //  map.put("status",status.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("UserComplaint").child(complaint.getC_id()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused)
                                    {
                                        Toast.makeText(holder.assign.getContext(), "data update successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                        notifyItemChanged(position);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e)
                                    {
                                        Toast.makeText(holder.assign.getContext(), "Error while updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });



                    }
                });


            }
        });


    }


    @Override
    public int getItemCount() {
        return usercomplaint.size();
    }

    public class  assignViewHolder extends RecyclerView.ViewHolder {
        TextView cDes,cArea,cLandmark,username,cStatus,assignTo;

        Button assign;
        public assignViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.CuName);
            cDes = itemView.findViewById(R.id.CDes);
            cArea=itemView.findViewById(R.id.CArea);
            cLandmark=itemView.findViewById(R.id.CLandmark);
            assign=itemView.findViewById(R.id.assignBtn);
            cStatus=itemView.findViewById(R.id.cStatus);
            assignTo=itemView.findViewById(R.id.cAssignTo);

        }


    }
}
