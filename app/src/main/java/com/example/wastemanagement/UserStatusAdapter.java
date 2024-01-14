package com.example.wastemanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserStatusAdapter extends RecyclerView.Adapter<UserStatusAdapter.UserStatusViewHolder> {


    @NonNull

    Context context;
    ArrayList<UserComplaint> complaints;



    public UserStatusAdapter(@NonNull Context context, ArrayList<UserComplaint> usercomplaint) {
        this.context = context;
        this.complaints = usercomplaint;
    }

    public UserStatusAdapter.UserStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_user_view_status_adapterpage,parent,false);

        return new UserStatusViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserStatusViewHolder holder, int position) {
        UserComplaint complaint=complaints.get(position);
        holder.username.setText(complaint.getUname());
        holder.cDes.setText(complaint.getComplaint_des());
        holder.cArea.setText(complaint.getComplaint_area());
        holder.cLandmark.setText(complaint.getComplaint_land());
        holder.status.setText(complaint.getStatus());
        holder.progress.setText(complaint.getC_progress());
        holder.assignto.setText(complaint.getC_assign());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("UserComplaint");

    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }

    public class  UserStatusViewHolder extends RecyclerView.ViewHolder {
        TextView cDes,cArea,cLandmark,username,status,progress,assignto;

        public UserStatusViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.CuName);
            cDes = itemView.findViewById(R.id.CDes);
            cArea=itemView.findViewById(R.id.CArea);
            cLandmark=itemView.findViewById(R.id.CLandmark);
            status=itemView.findViewById(R.id.C_Status);
            progress=itemView.findViewById(R.id.C_Progress);
            assignto=itemView.findViewById(R.id.C_Assign);
        }


    }
}
