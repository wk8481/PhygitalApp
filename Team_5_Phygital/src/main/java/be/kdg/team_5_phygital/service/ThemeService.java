package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Theme;
import be.kdg.team_5_phygital.repository.ThemeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public Optional<Theme> getThemeById(int id) {
        return themeRepository.findById(id);
    }

    public Theme getThemeByProjectId(int projectId) {
        return themeRepository.findByProjectId(projectId);
    }

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @Transactional
    public Theme saveTheme(String name, String information) {
        return themeRepository.save(new Theme(name, information));
    }
    
    public boolean updateTheme(int themeId, String name) {
        Theme theme = themeRepository.findById(themeId).orElse(null);
        if (theme == null) {
            return false;
        }
        theme.setName(name);
        themeRepository.save(theme);
        return true;
    }
}
