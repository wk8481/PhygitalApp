package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.repository.SubThemeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubThemeService {
    private SubThemeRepo subThemeRepo;

    public SubThemeService(SubThemeRepo subThemeRepo) {
        this.subThemeRepo = subThemeRepo;
    }


    public SubTheme addSubtheme(SubTheme subtheme) {
        return subThemeRepo.save(subtheme);
    }


    public Optional<SubTheme> getSubthemeById(int id) {
        return subThemeRepo.findById(id);
    }


    public List<SubTheme> getAllSubtheme() {
        return subThemeRepo.findAll();
    }

    public List<SubTheme> getSubthemeByFlowId(Flow flow) {return subThemeRepo.getSubThemesByFlow(flow);}
}
