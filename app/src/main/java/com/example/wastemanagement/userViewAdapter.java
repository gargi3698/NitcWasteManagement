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

public class userViewAdapter extends RecyclerView.Adapter<userViewAdapter.userViewViewHolder> {


    @NonNull

    Context context;
    ArrayList<UserComplaint> usercomplaint;



    public userViewAdapter(@NonNull Context context, ArrayList<UserComplaint> usercomplaint) {
        this.context = context;
        this.usercomplaint = usercomplaint;
    }

    public userViewAdapter.userViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_viewcomplaint_page,parent,false);

        return new userViewViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull userViewViewHolder holder, int position) {
        UserComplaint complaint=usercomplaint.get(position);
        holder.username.setText(complaint.getUname());
        holder.cDes.setText(complaint.getComplaint_des());
        holder.cArea.setText(complaint.getComplaint_area());
        holder.cLandmark.setText(complaint.getComplaint_land());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("UserComplaint");

    }

    @Override
    public int getItemCount() {
        return usercomplaint.size();
    }

    public class  userViewViewHolder extends RecyclerView.ViewHolder {
        TextView cDes,cArea,cLandmark,username;


        public userViewViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.CuName);
            cDes = itemView.findViewById(R.id.CDes);
            cArea=itemView.findViewById(R.id.CArea);
            cLandmark=itemView.findViewById(R.id.CLandmark);



        }


    }
}