package com.picon.agbsn;

/**
 * Created by user on 3/6/2017.
 */

public class User {


    public String id;
    public String name;
    public String fathername;
    public String age;
    public String address;
    public String city;
    public String state;
    public String enNumber;
    public String imageurl;
    public String education;
    public String other;
    public String mobile1;
    public String mobile2;
    public String occupations;
    public String marital;
    public String dob;
    public String dot;
    public String dop;
    public String height;
    public String manglik;
    public String caste;

    public User() {
    }

    public User(String id,
                String name,
                String fathername,
                String age,
                String address,
                String city,
                String state,
                String enNumber,
                String imageurl,
                String education,
                String other,
                String mobile1,
                String mobile2,
                String occupations,
                String marital,
                String dob,
                String dot,
                String dop,
                String height,
                String manglik,
                String caste

    ) {
        this.id = id;
        this.name = name;
        this.fathername = fathername;
        this.age = age;
        this.address = address;
        this.city = city;
        this.state = state;
        this.enNumber = enNumber;
        this.imageurl = imageurl;
        this.education = education;
        this.other = other;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.occupations = occupations;
        this.marital = marital;
        this.dob = dob;
        this.dot = dot;
        this.dop = dop;
        this.height = height;
        this.manglik = manglik;
        this.caste = caste;
    }
}