package com.example.wastemanagement;

public class UserFeedbackclass {
    public String uname,feedback_des,c_id;


    public UserFeedbackclass(){}

    public UserFeedbackclass(String uname,String feedback_des,String c_id)
    {
        this.uname=uname;
        this.feedback_des = feedback_des;
        //this.c_id=c_id;
    }

    public String getUname(){
        return uname;
    }

    public String getFeedback_des() {
        return feedback_des;
    }

}