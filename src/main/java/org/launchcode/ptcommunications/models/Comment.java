package org.launchcode.ptcommunications.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;
@Entity
public class Comment extends AbstractEntity {
    @ManyToOne
    private Student student;
    private String comment;
    public Comment(){

    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
