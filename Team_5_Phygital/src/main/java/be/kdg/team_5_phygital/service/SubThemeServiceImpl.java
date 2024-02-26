package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.repository.SubThemeRepo;

import java.util.List;
import java.util.Optional;

public class SubThemeServiceImpl implements SubThemeService{
    private SubThemeRepo subThemeRepo;

    public SubThemeServiceImpl(SubThemeRepo subThemeRepo) {
        this.subThemeRepo = subThemeRepo;
    }

    @Override
    public SubTheme addSubtheme(SubTheme subtheme) {
        return subThemeRepo.save(subtheme);
    }

    @Override
    public Optional<SubTheme> getSubthemeById(int id) {
        return subThemeRepo.findById(id);
    }

    @Override
    public List<SubTheme> getAllSubtheme() {
        return subThemeRepo.findAll();
    }
}
