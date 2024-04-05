package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.repository.FlowRepository;
import be.kdg.team_5_phygital.repository.SubThemeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubThemeService {
    private final SubThemeRepository subThemeRepository;
    private final FlowRepository flowRepository;

    public SubThemeService(SubThemeRepository subThemeRepository, FlowRepository flowRepository) {
        this.subThemeRepository = subThemeRepository;
        this.flowRepository = flowRepository;
    }

    public SubTheme addSubtheme(SubTheme subtheme) {
        return subThemeRepository.save(subtheme);
    }


    public Optional<SubTheme> getSubThemeById(int id) {
        return subThemeRepository.findById(id);
    }


    public List<SubTheme> getAllSubTheme() {
        return subThemeRepository.findAll();
    }

    public List<SubTheme> getSubThemeByFlowId(Flow flow) {return subThemeRepository.getSubThemesByFlow(flow);}

    @Transactional
    public SubTheme saveSubTheme(String name, String information, int flowId) {
        Flow flow = flowRepository.findById(flowId).orElse(null);
        return subThemeRepository.save(new SubTheme(name, information, flow));
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

    @Transactional
    public boolean deleteSubTheme(int subThemeId) {
        Optional<SubTheme> subTheme = subThemeRepository.findById(subThemeId);
        if (subTheme.isEmpty()) {
            return false;
        }
        subThemeRepository.deleteById(subThemeId);
        return true;
    }


}
