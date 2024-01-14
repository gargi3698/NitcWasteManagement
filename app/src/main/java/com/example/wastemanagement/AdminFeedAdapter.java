package com.example.wastemanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminFeedAdapter extends RecyclerView.Adapter<AdminFeedAdapter.adminFeedViewHolder> {


    @NonNull

    Context context;
    ArrayList<UserFeedbackclass> feedbacks ;



    public AdminFeedAdapter(@NonNull Context context, ArrayList<UserFeedbackclass> feedbacks) {
        this.context = context;
        this.feedbacks = feedbacks;
    }

    public AdminFeedAdapter.adminFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_admin_feedback_design,parent,false);

        return new adminFeedViewHolder(view);

    }


    public void onBindViewHolder(@NonNull adminFeedViewHolder holder, int position) {
        UserFeedbackclass nfeedback=feedbacks.get(position);
        holder.name.setText(nfeedback.getUname());
        holder.feedback.setText(nfeedback.getFeedback_des());

    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }

    public class  adminFeedViewHolder extends RecyclerView.ViewHolder {
        TextView name,feedback;

        public adminFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.feedName);
            feedback = itemView.findViewById(R.id.feedback);




        }


    }
}