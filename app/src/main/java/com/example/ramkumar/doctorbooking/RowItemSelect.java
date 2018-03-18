package com.example.ramkumar.doctorbooking;

/**
 * Created by Ram Kumar on 3/17/2018.
 */
public class RowItemSelect {

    /*  For Patient Registeration   */
    private String patientName, patientPhno, patientEmail, patientPassword, patientDesease;

    public RowItemSelect(String patientName, String patientPhno, String patientEmail, String patientPassword, String patientDesease) {
        this.patientName = patientName;
        this.patientPhno = patientPhno;
        this.patientEmail = patientEmail;
        this.patientPassword = patientPassword;
        this.patientDesease = patientDesease;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPhno() {
        return patientPhno;
    }

    public void setPatientPhno(String patientPhno) {
        this.patientPhno = patientPhno;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }

    public String getPatientDesease() {
        return patientDesease;
    }

    public void setPatientDesease(String patientDesease) {
        this.patientDesease = patientDesease;
    }

    /*  For Doctor Registeration    */
    private String doctorName;
    private String doctorPhno;
    private String doctorEmail;
    private String doctorPassword;
    private String doctorSpecial;
    private String availableFrom;
    private String availableTo;

    public RowItemSelect(String doctorName, String doctorPhno, String doctorEmail, String doctorPassword, String doctorSpecial, String availableFrom, String availableTo) {
        this.doctorName = doctorName;
        this.doctorPhno = doctorPhno;
        this.doctorEmail = doctorEmail;
        this.doctorPassword = doctorPassword;
        this.doctorSpecial = doctorSpecial;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhno() {
        return doctorPhno;
    }

    public void setDoctorPhno(String doctorPhno) {
        this.doctorPhno = doctorPhno;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    public String getDoctorSpecial() {
        return doctorSpecial;
    }

    public void setDoctorSpecial(String doctorSpecial) {
        this.doctorSpecial = doctorSpecial;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
    }

    /*  For Doctor Login    */
    public RowItemSelect(String doctorPhno, String doctorPassword, int a) {
        this.doctorPhno = doctorPhno;
        this.doctorPassword = doctorPassword;
    }

    /*  For Patient Login    */
    public RowItemSelect(String patientPhno, String patientPassword) {
        this.patientPhno = patientPhno;
        this.patientPassword = patientPassword;
    }

    /*  For Doctor List */
    public RowItemSelect(String doctorName, String doctorMobile, String special, String from, String to, int a) {
        this.doctorName = doctorName;
        this.doctorPhno = doctorMobile;
        this.doctorSpecial = special;
        this.availableFrom = from;
        this.availableTo = to;

    }

    /*  For Patient List    */
    public RowItemSelect(String patientName, String patientPhno, String patientDesease){
        this.patientName = patientName;
        this.patientPhno = patientPhno;
        this.patientDesease = patientDesease;
    }

}
