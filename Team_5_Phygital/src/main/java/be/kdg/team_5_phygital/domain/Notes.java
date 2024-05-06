package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "notes")
public class Notes {

    @Id
    private int id;

    private String note;

    public Notes(String note) {
        this.note = note;
    }

    public Notes() {

    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
