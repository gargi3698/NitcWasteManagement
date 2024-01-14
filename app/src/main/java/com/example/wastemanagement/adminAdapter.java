package com.example.wastemanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminAdapter extends RecyclerView.Adapter<adminAdapter.adminViewHolder> {


    @NonNull

    Context context;
    ArrayList<UserComplaint> usercomplaint;
    Button approve,disapprove,delete;


    public adminAdapter(@NonNull Context context, ArrayList<UserComplaint> usercomplaint) {
        this.context = context;
        this.usercomplaint = usercomplaint;
    }

    public adminAdapter.adminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_allcomplaint_page,parent,false);
        
        return new adminViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull adminViewHolder holder, int position) {
        UserComplaint complaint=usercomplaint.get(position);
        holder.username.setText(complaint.getUname());
        holder.cDes.setText(complaint.getComplaint_des());
        holder.cArea.setText(complaint.getComplaint_area());
        holder.cLandmark.setText(complaint.getComplaint_land());
        holder.cStatus.setText(complaint.getStatus());
        holder.cProgress.setText(complaint.getC_progress());
        holder.cAssign.setText(complaint.getC_assign());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("UserComplaint");
        DatabaseReference reference2 = database.getReference("ComplaintSummary");
        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Complain approved successfully!", Toast.LENGTH_SHORT).show();
                complaint.setStatus("APPROVED");
                reference.child(complaint.getC_id()).setValue(complaint);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long approve = (long) snapshot.child("approveC").getValue();

                        reference2.child("approveC").setValue(++approve);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        holder.disapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Complain disapproved successfully!", Toast.LENGTH_SHORT).show();
                complaint.setStatus("DISAPPROVED");
                reference.child(complaint.getC_id()).setValue(complaint);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long disapprove = (long) snapshot.child("disapproveC").getValue();

                        reference2.child("disapproveC").setValue(++disapprove);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }


    @Override
    public int getItemCount() {
        return usercomplaint.size();
    }

    public class  adminViewHolder extends RecyclerView.ViewHolder {
        TextView cDes,cArea,cLandmark,username,cStatus,cProgress,cAssign;

        Button approve,disapprove;
        public adminViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.CuName);
            cDes = itemView.findViewById(R.id.CDes);
            cArea=itemView.findViewById(R.id.CArea);
            cLandmark=itemView.findViewById(R.id.CLandmark);
            cStatus=itemView.findViewById(R.id.CStatus);
            cProgress=itemView.findViewById(R.id.CProgress);
            cAssign=itemView.findViewById(R.id.CAssign);
            approve=itemView.findViewById(R.id.approveCBtn);
            disapprove=itemView.findViewById(R.id.disapproveCBtn);


        }


    }
}
