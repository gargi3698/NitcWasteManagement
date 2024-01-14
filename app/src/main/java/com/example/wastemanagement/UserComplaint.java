package com.example.wastemanagement;

public class UserComplaint {

    public String uname,complaint_des,complaint_area,complaint_land,status,c_id,c_assign,c_progress;


    public UserComplaint(){}

    public UserComplaint(String uname,String complaint_des,String complaint_area,String complaint_land,String status,String c_id,String c_assign,String c_progress)
    {
        this.uname=uname;
        this.complaint_des = complaint_des;
        this.complaint_area = complaint_area;
        this.complaint_land = complaint_land;
        this.status=status;
        this.c_id=c_id;
        this.c_assign=c_assign;
        this.c_progress=c_progress;

    }

    public String getC_progress() {
        return c_progress;
    }

    public void setC_progress(String c_progress) {
        this.c_progress = c_progress;
    }

    public String getC_assign() {
        return c_assign;
    }

    public void setC_assign(String c_assign) {
        this.c_assign = c_assign;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getUname(){
        return uname;
    }

    public String getComplaint_des() {
        return complaint_des;
    }

    public String getComplaint_area() {
        return complaint_area;
    }

    public String getComplaint_land() {
        return complaint_land;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

