package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.Theme;
import be.kdg.team_5_phygital.repository.ProjectRepository;
import be.kdg.team_5_phygital.repository.ThemeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ProjectRepository projectRepository;

    public ThemeService(ThemeRepository themeRepository, ProjectRepository projectRepository) {
        this.themeRepository = themeRepository;
        this.projectRepository = projectRepository;
    }

    public Theme getTheme(int themeId) {
        return themeRepository.findById(themeId).orElse(null);
    }

    public Theme getThemeByProjectId(int projectId) {
        return themeRepository.findByProjectId(projectId);
    }

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @Transactional
    public Theme saveTheme(String name, String information, int projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        return themeRepository.save(new Theme(name, information, project));
    }

    public boolean updateTheme(int themeId, String name, String information, String mediaUrl) {
        Theme theme = themeRepository.findById(themeId).orElse(null);
        if (theme == null) {
            return false;
        }
        theme.setName(name);
        theme.setInformation(information);
        theme.setMediaUrl(mediaUrl);
        themeRepository.save(theme);
        return true;
    }

    @Transactional
    public boolean deleteTheme(int themeId) {
        Optional<Theme> theme = themeRepository.findById(themeId);
        if (theme.isEmpty()) {
            return false;
        }
        themeRepository.deleteById(themeId);
        return true;
    }
}
