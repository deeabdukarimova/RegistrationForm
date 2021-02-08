package com.example.registrationform;

public class StudentsContact {
    /*The Java Class which will show data related to Students*/
    private  String LastName,name,email,country,gender;

    /*Creating constructors and getters and setters*/

    public StudentsContact(String lastName, String name, String email, String country, String gender) {
        LastName = lastName;
        this.name = name;
        this.email = email;
        this.country = country;
        this.gender = gender;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
