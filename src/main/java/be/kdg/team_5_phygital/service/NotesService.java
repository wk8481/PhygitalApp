package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Notes;
import be.kdg.team_5_phygital.domain.Session;
import be.kdg.team_5_phygital.repository.NotesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class NotesService {
    private final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }
    @Transactional
    public Notes createNote(String note){
        return notesRepository.save(new Notes(note));
    }

    @Transactional
    public void updateNote(Notes note, String newNote){
        note.setNote(newNote);
        notesRepository.save(note);
    }
}
