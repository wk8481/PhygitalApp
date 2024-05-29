package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.Session;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.repository.FlowRepository;
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

    public SubThemeService(SubThemeRepository subThemeRepository, FlowRepository flowRepository) {
        this.subThemeRepository = subThemeRepository;
        this.flowRepository = flowRepository;
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

    public void uploadMediaFiles(int subThemeId, List<MultipartFile> files) throws IOException {
        SubTheme subTheme = subThemeRepository.findById(subThemeId).orElse(null);
        if (subTheme == null) {
            // Handle error: Sub theme not found
            return;
        }

        // Process and save each uploaded file
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            // Save the file to a directory
            File destFile = new File("Team_5_Phygital/src/main/resources/static/media/" + fileName);
            file.transferTo(destFile);
            // Add the file to the sub theme's list of media files
            subTheme.addMediaFile(fileName);
        }

        // Save the updated sub theme
        subThemeRepository.save(subTheme);
    }

    public void deleteMediaFiles(int subThemeId, List<String> fileNames) throws IOException {
        SubTheme subTheme = subThemeRepository.findById(subThemeId).orElse(null);
        if (subTheme == null) {
            // Handle error: Sub theme not found
            return;
        }

        // Delete each specified media file
        for (String fileName : fileNames) {
            // Delete the file from the directory
            File file = new File("Team_5_Phygital/src/main/resources/static/media/" + fileName);
            if (file.exists()) {
                file.delete();
            }
            // Remove the file from the sub theme's list of media files
            subTheme.removeMediaFile(fileName);
        }

        // Save the updated sub theme
        subThemeRepository.save(subTheme);
    }
}
