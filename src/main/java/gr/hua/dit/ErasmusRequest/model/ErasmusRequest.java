package gr.hua.dit.ErasmusRequest.model;

import javax.persistence.*;

@Entity
@Table(name="erasmus_Request")
public class ErasmusRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="surname")
    private String surname;
    @Column(name="birthdate")
    private char birthdate;
    @Column(name="grades")
    private float grades;
    @Column(name="createdBy")
    private String createdBy;
    @Column(name="field")
    private String field;
    @Column(name="sendTo")
    private String sendTo;
    @Column(name="status")
    private String status;

    public ErasmusRequest(){

    }
    public ErasmusRequest(String firstName, String surname, char birthdate, float grades, String createdBy, String field, String sendTo, String status) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthdate = birthdate;
        this.grades=grades;
        this.createdBy = createdBy;
        this.field = field;
        this.sendTo = sendTo;
        this.status = status;
    }

    public float getGrades() {
        return grades;
    }

    public void setGrades(float grades) {
        this.grades = grades;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(char birthdate) {
        this.birthdate = birthdate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }


}
