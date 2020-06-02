package org.launchcode.ptcommunications.models;


import javax.persistence.OneToMany;
import org.launchcode.ptcommunications.repository.StudentRepository;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Student extends AbstractEntity{

    @NotNull
    private String name;
    @NotNull
    private int grade;
    @NotNull
    private String gender;
    @NotNull
    private String PrimaryContactName;
    @NotNull
    private String ContactNumber;
    @NotNull
    private String email;
    @NotNull
    private String HomeAddress;
    @OneToMany(mappedBy = "student")
    private List<Comment> commentList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPrimaryContactName() {
        return PrimaryContactName;
    }

    public void setPrimaryContactName(String primaryContactName) {
        PrimaryContactName = primaryContactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        HomeAddress = homeAddress;
    }





}
