package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.repository.SubThemeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubThemeService {
    private SubThemeRepository subThemeRepository;

    public SubThemeService(SubThemeRepository subThemeRepository) {
        this.subThemeRepository = subThemeRepository;
    }


    public SubTheme addSubtheme(SubTheme subtheme) {
        return subThemeRepository.save(subtheme);
    }


    public Optional<SubTheme> getSubthemeById(int id) {
        return subThemeRepository.findById(id);
    }


    public List<SubTheme> getAllSubtheme() {
        return subThemeRepository.findAll();
    }

    public List<SubTheme> getSubthemeByFlowId(Flow flow) {return subThemeRepository.getSubThemesByFlow(flow);}

    @Transactional
    public SubTheme saveSubTheme(String name, String information) {
        return subThemeRepository.save(new SubTheme(name, information));
    }

    public boolean updateSubTheme(int subThemeId, String name, String information) {
        SubTheme subTheme = subThemeRepository.findById(subThemeId).orElse(null);
        if (subTheme == null) {
            return false;
        }
        subTheme.setName(name);
        subTheme.setInformation(information);
        subThemeRepository.save(subTheme);
        return true;
    }
}
