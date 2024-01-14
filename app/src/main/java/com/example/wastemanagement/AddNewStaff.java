package com.example.wastemanagement;

import android.widget.EditText;

public class AddNewStaff {

    public String staff_name,staff_phone,staff_emailid,staff_type,staff_location,staff_status,sid;

    public AddNewStaff(EditText staff_name, EditText staff_phone, EditText staff_emailid, EditText staff_type,EditText staff_location,EditText  ss,String sid){}

    public AddNewStaff() {
    }

    public AddNewStaff(String staff_name, String staff_phone, String staff_emailid, String staffType,String staff_location,String  ss,String sid)
    {
        this.staff_name = staff_name;
        this.staff_phone = staff_phone;
        this.staff_emailid = staff_emailid;
        this.staff_type = staffType;
        this.staff_location=staff_location;
        this.staff_status=ss;
        this.sid=sid;

    }


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStaff_location() {
        return staff_location;
    }

    public String getStaff_status() {
        return staff_status;
    }

    public String getStaff_name() { return staff_name; }

    public String getStaff_phone() {
        return staff_phone;
    }

    public String getStaff_emailid () {
        return staff_emailid;
    }

    public String getStaff_type() {
        return staff_type;
    }
}
