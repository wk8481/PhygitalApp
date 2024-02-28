package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SubTheme;

import java.util.List;
import java.util.Optional;

public interface SubThemeService {

    SubTheme addSubtheme(SubTheme subtheme);

    Optional<SubTheme> getSubthemeById(int id);

    List<SubTheme> getAllSubtheme();
}
