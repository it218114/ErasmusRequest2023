package gr.hua.dit.ErasmusRequest.model;

import javax.persistence.*;

@Entity
@Table(name="erasmus_Committee")
public class ErasmusCommittee {
    @Id
    @Column(name="email")
    private String email;
    @Column(name="name")
    private String name;
    @Column(name="field")
    private String field;

    public ErasmusCommittee(String email, String name, String field) {
        this.email = email;
        this.name = name;
        this.field = field;
    }
    public ErasmusCommittee(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
