package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Integer> {
}
