package com.project.customerrelationshipmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @NotEmpty(message = "This field must not be empty!")
    @Size(max = 50,message = "Only 50 characters are allowed")
    private String userName;
    @Column(unique = true)
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email format")
    private String userEmail;
    private String userImageUrl;
    @NotBlank(message = "This field must not be empty!")
//    @Size(min = 6, max = 16,message = "Minimum 6 and maximum 16 characters are allowed")
    private String userPassword;
    @AssertTrue(message = "Agree to the terms and conditions for signing up")
    private boolean userEnabled;
    private String userRole;
    @Lob
    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "This field must not be empty!")
    @Size(min = 10,max = 10000,message = "Minimum 10 and maximum 10000 characters are allowed")
    private String userAbout;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    private List<Contact> contactList = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(String userAbout) {
        this.userAbout = userAbout;
    }

    public boolean isUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(boolean userEnabled) {
        this.userEnabled = userEnabled;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userImageUrl='" + userImageUrl + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEnabled=" + userEnabled +
                ", userRole='" + userRole + '\'' +
                ", userAbout='" + userAbout + '\'' +
                '}';
    }
}
