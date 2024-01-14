package com.example.wastemanagement;

public class SupervisorDetails {

    public String supervisorname,supervisoremail,supervisormobileNo,attendance,supervisorZone;

    public SupervisorDetails(){}

    public SupervisorDetails(String supervisorname,String supervisoremail,String supervisormobileNo,String supervisorZone, String attendance) {

        this.supervisorname = supervisorname;
        this.supervisoremail = supervisoremail;
        this.supervisormobileNo = supervisormobileNo;
        this.supervisorZone=supervisorZone;
        this.attendance=attendance;
    }

    public String getSupervisorZone() {
        return supervisorZone;
    }

    public void setSupervisorZone(String supervisorZone) {
        this.supervisorZone = supervisorZone;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getSupervisorname() {
        return supervisorname;
    }

    public void setSupervisorname(String supervisorname) {
        this.supervisorname = supervisorname;
    }

    public String getSupervisoremail() {
        return supervisoremail;
    }

    public void setSupervisoremail(String supervisoremail) {
        this.supervisoremail = supervisoremail;
    }

    public String getSupervisormobileNo() {
        return supervisormobileNo;
    }

    public void setSupervisormobileNo(String supervisormobileNo) {
        this.supervisormobileNo = supervisormobileNo;
    }
}