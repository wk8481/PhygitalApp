package be.kdg.team_5_phygital.domain;


import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;

    // Standard getters and setters


    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }


    public String getAuthority() {
        return authority;
    }


    public User getUser() {
        return user;
    }


}

