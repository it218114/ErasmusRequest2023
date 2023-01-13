package gr.hua.dit.ErasmusRequest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="it")
    private String it;
    @Column(name="first_name")
    private String firstName;
    @Column(name="surname")
    private String surname;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;
    @Column(name="email")
    private String email;

    public User(String it, String firstName, String surname, String password, String role,String email) {
        super();
        this.it = it;
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
        this.role = role;
        this.email=email;
    }
    public User(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String it, String firstName, String surname, String password) {
        this.it = it;
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return it == user.it;
    }

    @Override
    public int hashCode() {
        return Objects.hash(it);
    }
}
