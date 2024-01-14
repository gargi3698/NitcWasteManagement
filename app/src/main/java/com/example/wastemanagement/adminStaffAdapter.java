package com.example.wastemanagement;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adminStaffAdapter extends RecyclerView.Adapter<adminStaffAdapter.adminStaffViewHolder> {


    @NonNull

    Context context;
    ArrayList<AddNewStaff> staffs ;
    String [] supervisors={"Supervisor1","Supervisor2","Supervisor3","Supervisor4","Supervisor5"};

    public adminStaffAdapter(@NonNull Context context, ArrayList<AddNewStaff> staff) {
        this.context = context;
        this.staffs = staff;
    }

    public adminStaffAdapter.adminStaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_allstaff_page,parent,false);

        return new adminStaffViewHolder(view);

    }


    public void onBindViewHolder(@NonNull adminStaffViewHolder holder, int position ) {
        AddNewStaff nStaff = staffs.get(position);
        holder.name.setText(nStaff.getStaff_name());
        holder.phone.setText(nStaff.getStaff_phone());
        holder.email.setText(nStaff.getStaff_emailid());
        holder.type.setText(nStaff.getStaff_type());
        holder.location.setText(nStaff.getStaff_location());
        holder.status.setText(nStaff.getStaff_status());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("AddNewStaff");
        DatabaseReference reference2 = database.getReference("ComplaintSummary");

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   final DialogPlus dialogPlus=DialogPlus.newDialog(holder.status.getContext())
                           .setContentHolder(new ViewHolder(R.layout.edit_staff_content))
                           .setExpanded(true,1500)
                           .create();
                   View editView= dialogPlus.getHolderView();
                   EditText name=editView.findViewById(R.id.sName);
                   EditText phone=editView.findViewById(R.id.sPhone);
                   EditText email=editView.findViewById(R.id.sEmail);
                   EditText type=editView.findViewById(R.id.sType);
                   EditText location=editView.findViewById(R.id.sLocation);
                   EditText status=editView.findViewById(R.id.sStatus);
                   Button update=editView.findViewById(R.id.usubmit);

                   name.setText(nStaff.getStaff_name());
                   phone.setText(nStaff.getStaff_phone());
                   email.setText(nStaff.getStaff_emailid());
                   type.setText(nStaff.getStaff_type());
                   location.setText(nStaff.getStaff_location());
                   status.setText(nStaff.getStaff_status());
//                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        long total = (long) snapshot.child("total").getValue();
//
//                        status.setText(String.valueOf(total));
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

                   dialogPlus.show();
                   update.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Map<String,Object> map=new HashMap<>();
                           map.put("staff_name",name.getText().toString());
                           map.put("staff_phone",phone.getText().toString());
                           map.put("staff_emailid",email.getText().toString());
                           map.put("staff_type",type.getText().toString());
                           map.put("staff_location",location.getText().toString());
                           map.put("staff_status",status.getText().toString());
                           map.put("sid",nStaff.getSid().toString());
                           FirebaseDatabase.getInstance().getReference().child("AddNewStaff")
                                   .child(nStaff.getSid()).updateChildren(map)

                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                         Toast.makeText(holder.name.getContext(), "staff details update successfully", Toast.LENGTH_SHORT).show();
                                         dialogPlus.dismiss();
                                         notifyItemChanged(position);
                                       }
                                   })
                                   .addOnFailureListener(new OnFailureListener() {
                                       @Override
                                       public void onFailure(@NonNull Exception e) {
                                           Toast.makeText(holder.name.getContext(), "staff details cannot be updated!", Toast.LENGTH_SHORT).show();
                                           dialogPlus.dismiss();
                                       }
                                   });

                       }
                   });
            }
        });
        holder.delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("deletePress","delete pressed ");
                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("Delete Staff")
                        .setMessage("Are you sure you want to delete this staff")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("AddNewStaff")
                                        .child(nStaff.getSid()).removeValue();
                                staffs.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                        notifyDataSetChanged();
                return true;

            }
        });
    }

    @Override
    public int getItemCount() {
        return staffs.size();
    }

    public class  adminStaffViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,email,type,location,status;
        Button delete ,edit;


        public adminStaffViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.SName);
            phone = itemView.findViewById(R.id.SPhone);
            email=itemView.findViewById(R.id.SEmail);
            type=itemView.findViewById(R.id.SType);
            location=itemView.findViewById(R.id.S_Location);
            status=itemView.findViewById(R.id.S_Status);
            delete=itemView.findViewById(R.id.delStaffBtn);
            edit = (Button)itemView.findViewById(R.id.editStaffBtn);


        }


    }
}
