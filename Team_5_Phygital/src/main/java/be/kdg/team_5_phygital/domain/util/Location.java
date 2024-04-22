package be.kdg.team_5_phygital.domain.util;

import be.kdg.team_5_phygital.domain.Flow;
import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String city;

    private int longitude;

    private int latitude;

    @OneToOne
    @JoinColumn(name = "installation_id")
    private Flow flow;

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public Location() {
    }

    public Location(int longitude, int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location(String city, int longitude, int latitude) {
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
}
