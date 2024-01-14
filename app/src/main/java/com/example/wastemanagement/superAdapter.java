package com.example.wastemanagement;

import android.content.Context;
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

public class superAdapter extends RecyclerView.Adapter<superAdapter.superViewViewHolder> {


    @NonNull

    Context context;
    ArrayList<UserComplaint> usercomplaint;



    public superAdapter(@NonNull Context context, ArrayList<UserComplaint> usercomplaint) {
        this.context = context;
        this.usercomplaint = usercomplaint;
    }

    public superAdapter.superViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.supervisor_allcomplaint,parent,false);

        return new superViewViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull superViewViewHolder holder, int position) {
        UserComplaint complaint=usercomplaint.get(position);
        holder.username.setText(complaint.getUname());
        holder.cDes.setText(complaint.getComplaint_des());
        holder.cArea.setText(complaint.getComplaint_area());
        holder.cLandmark.setText(complaint.getComplaint_land());
        holder.cStatus.setText(complaint.getStatus());
        holder.cProgress.setText(complaint.getC_progress());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("UserComplaint");
        DatabaseReference reference2 = database.getReference("ComplaintSummary");


        holder.solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Complain marked as solved", Toast.LENGTH_SHORT).show();
                complaint.setC_progress("SOLVED");
                reference.child(complaint.getC_id()).setValue(complaint);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long solved = (long) snapshot.child("solvedC").getValue();

                        reference2.child("solvedC").setValue(++solved);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        holder.unsolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Complain marked as unsolved", Toast.LENGTH_SHORT).show();
                complaint.setC_progress("UNSOLVED");
                reference.child(complaint.getC_id()).setValue(complaint);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long unsolved = (long) snapshot.child("unsolvedC").getValue();

                        reference2.child("unsolvedC").setValue(++unsolved);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        holder.inprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Complain marked as inprogress", Toast.LENGTH_SHORT).show();
                complaint.setC_progress("IN PROGRESS");
                reference.child(complaint.getC_id()).setValue(complaint);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long unsolved = (long) snapshot.child("inProgressC").getValue();

                        reference2.child("inProgressC").setValue(++unsolved);
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

    public class  superViewViewHolder extends RecyclerView.ViewHolder {
        TextView cDes,cArea,cLandmark,username,cStatus,cProgress;

        Button solve,unsolve,inprogress;
        public superViewViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.CuName);
            cDes = itemView.findViewById(R.id.CDes);
            cArea=itemView.findViewById(R.id.CArea);
            cLandmark=itemView.findViewById(R.id.CLandmark);
            cStatus=itemView.findViewById(R.id.comStatus);
            cProgress=itemView.findViewById(R.id.comProgress);
            solve=itemView.findViewById(R.id.solveBtn);
            unsolve=itemView.findViewById(R.id.unsolveBtn);
            inprogress=itemView.findViewById(R.id.inProgressBtn);


        }


    }
}