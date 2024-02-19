package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
public class UserRole {

    @Id
    private int userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;


    public UserRole(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;

    }
    public UserRole() {
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }



}
