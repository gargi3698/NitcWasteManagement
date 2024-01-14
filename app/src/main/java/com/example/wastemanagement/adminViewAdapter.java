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

public class adminViewAdapter extends RecyclerView.Adapter<adminViewAdapter.adminViewViewHolder> {


    @NonNull

    Context context;
    ArrayList<UserComplaint> usercomplaint;


    public adminViewAdapter(@NonNull Context context, ArrayList<UserComplaint> usercomplaint) {
        this.context = context;
        this.usercomplaint = usercomplaint;
    }

    public adminViewAdapter.adminViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_admin_view_cadapter,parent,false);

        return new adminViewViewHolder(view);

    }

    public void onBindViewHolder(@NonNull adminViewViewHolder holder, int position) {

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

        holder.delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("Delete Complaint")
                        .setMessage("Are you sure want to delete this complaint?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                usercomplaint.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return usercomplaint.size();
    }

    public class  adminViewViewHolder extends RecyclerView.ViewHolder {
        TextView cDes,cArea,cLandmark,username,cStatus,cProgress,cAssign;

        Button delete;
        public adminViewViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.CuName);
            cDes = itemView.findViewById(R.id.CDes);
            cArea=itemView.findViewById(R.id.CArea);
            cLandmark=itemView.findViewById(R.id.CLandmark);
            cStatus=itemView.findViewById(R.id.CStatus);
            cProgress=itemView.findViewById(R.id.CProgress);
            cAssign=itemView.findViewById(R.id.CAssign);
            delete=itemView.findViewById(R.id.deleteCBtn);


        }


    }
}
