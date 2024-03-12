package com.project.customerrelationshipmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contactId;
    @NotBlank(message = "This field must not be empty!")
    @Size(max=50,message = "Contact name should contain 50 character")
    private String contactName;
    private String contactNickName;
    @NotBlank(message = "This field must not be empty!")
    private String contactWork;
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email format")
    private String contactEmail;
    @NotBlank(message = "This field must not be empty!")
    @Size(max = 10,message = "Invalid phone number")
    private String contactPhone;
    private String contactImage;
//    @Column(length = 10000)
    @Lob
    @Column(columnDefinition = "TEXT")
    private String contactDescription;
    @ManyToOne
    private User user;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactWork() {
        return contactWork;
    }

    public void setContactWork(String contactWork) {
        this.contactWork = contactWork;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactImage() {
        return contactImage;
    }

    public void setContactImage(String contactImage) {
        this.contactImage = contactImage;
    }

    public String getContactDescription() {
        return contactDescription;
    }

    public void setContactDescription(String contactDescription) {
        this.contactDescription = contactDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactFirstName) {
        this.contactName = contactFirstName;
    }

    public String getContactNickName() {
        return contactNickName;
    }

    public void setContactNickName(String contactLastName) {
        this.contactNickName = contactLastName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", contactName='" + contactName + '\'' +
                ", contactNickName='" + contactNickName + '\'' +
                ", contactWork='" + contactWork + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactImage='" + contactImage + '\'' +
                ", contactDescription='" + contactDescription + '\'' +
                ", user=" + user +
                '}';
    }
}
