package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.repository.FlowRepository;
import be.kdg.team_5_phygital.repository.SessionRepository;
import be.kdg.team_5_phygital.repository.SubThemeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SubThemeService {
    private final SubThemeRepository subThemeRepository;
    private final FlowRepository flowRepository;
    private final QuestionService questionService;
    private final SessionRepository sessionRepository;

    public SubThemeService(SubThemeRepository subThemeRepository, FlowRepository flowRepository, QuestionService questionService, SessionRepository sessionRepository) {
        this.subThemeRepository = subThemeRepository;
        this.flowRepository = flowRepository;
        this.questionService = questionService;
        this.sessionRepository = sessionRepository;
    }

    public SubTheme getSubTheme(int subThemeId) {
        return subThemeRepository.findById(subThemeId).orElse(null);
    }

    public SubTheme getSubThemeByName(String name) {
        return subThemeRepository.findByName(name).orElse(null);
    }

    public List<SubTheme> getAllSubThemes() {
        return subThemeRepository.findAll();
    }

    public List<SubTheme> getSubThemeByFlowId(int flowId) {return subThemeRepository.getSubThemesByFlowId(flowId);}

    @Transactional
    public SubTheme saveSubTheme(String name, String information, int flowId) {
        Flow flow = flowRepository.findById(flowId).orElse(null);
        return subThemeRepository.save(new SubTheme(name, information, flow));
    }

    public boolean updateSubTheme(int subThemeId, String name, String information, boolean isVisible, String mediaUrl) {
        SubTheme subTheme = subThemeRepository.findById(subThemeId).orElse(null);
        if (subTheme == null) {
            return false;
        }
        subTheme.setName(name);
        subTheme.setInformation(information);
        subTheme.setVisible(isVisible);
        subTheme.setMediaUrl(mediaUrl);
        subThemeRepository.save(subTheme);
        return true;
    }

    @Transactional
    public boolean deleteSubTheme(int subThemeId) {
        Optional<SubTheme> subTheme = subThemeRepository.findById(subThemeId);
        if (subTheme.isEmpty()) {
            return false;
        }
        for (Question question : questionService.getQuestionsBySubTheme(subTheme.get())) {
            questionService.deleteQuestion(question.getId());
        }
        sessionRepository.deleteAll(sessionRepository.getSessionsBySubTheme(subTheme.get()));
        subThemeRepository.deleteById(subThemeId);
        return true;
    }
}
