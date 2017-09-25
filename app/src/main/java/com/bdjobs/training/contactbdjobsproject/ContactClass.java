package com.bdjobs.training.contactbdjobsproject;

/**
 * Created by FIROZ HASAN on 7/31/2017.
 */

public class ContactClass {

    String name, phoneNumber, email, phonetype, emailtype;
    int id;



    public ContactClass(String name, String phoneNumber, String email, String phonetype, String emailtype, int id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.phonetype = phonetype;
        this.emailtype = emailtype;
        this.id = id;
    }

    public ContactClass(String name, String phoneNumber, String email, String phonetype, String emailtype) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.phonetype = phonetype;
        this.emailtype = emailtype;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonetype() {
        return phonetype;
    }

    public void setPhonetype(String phonetype) {
        this.phonetype = phonetype;
    }

    public String getEmailtype() {
        return emailtype;
    }

    public void setEmailtype(String emailtype) {
        this.emailtype = emailtype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
