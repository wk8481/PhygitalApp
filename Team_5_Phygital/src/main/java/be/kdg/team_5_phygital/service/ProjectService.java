package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.controller.api.dto.UpdateProjectDto;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.Theme;
import be.kdg.team_5_phygital.repository.ProjectRepository;
import be.kdg.team_5_phygital.repository.SharingPlatformRepository;
import be.kdg.team_5_phygital.repository.ThemeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final SharingPlatformRepository sharingPlatformRepository;
    private final ThemeRepository themeRepository;

    public ProjectService(ProjectRepository projectRepository, SharingPlatformRepository sharingPlatformRepository, ThemeRepository themeRepository) {
        this.projectRepository = projectRepository;
        this.sharingPlatformRepository = sharingPlatformRepository;
        this.themeRepository = themeRepository;
    }

    public Project getProject(int id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project getProjectByName(String name) {
        return projectRepository.findByName(name).orElse(null);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectBySharingPlatformId(int sharingPlatformId) {
        return projectRepository.findAllBySharingPlatformId(sharingPlatformId);
    }

    public List<Project> searchProjectsByNameLike(String searchTerm) {
        return projectRepository.findProjectsByNameLikeIgnoreCase("%" + searchTerm + "%");
    }

    public void updateTimeAndParticipants(Project project, float time){
        projectRepository.updateProjectTimeAndParticipants(project.getId(), time);
    }

    @Transactional
    public Project saveProject(String name, String backgroundColorHex, String fontName, int sharingPlatformId) {
        SharingPlatform sharingPlatform = sharingPlatformRepository.findById(sharingPlatformId).orElse(null);
        Project project = new Project(name, backgroundColorHex, fontName, sharingPlatform);
        themeRepository.save(new Theme("Unnamed Theme", "", project));
        return projectRepository.save(project);
    }

    @Transactional
    public boolean updateProject(int projectId, UpdateProjectDto updateProjectDto, MultipartFile logoFile) throws IOException {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            return false;
        }
        project.setName(updateProjectDto.getName());
        project.setBackgroundColorHex(updateProjectDto.getBackgroundColorHex());
        project.setFontName(updateProjectDto.getFontName());
        project.setPublic(updateProjectDto.isPublic());

        // Handle logo file upload
        if (logoFile != null && !logoFile.isEmpty()) {
            // Save the logo file and update the logoPath
            String savedLogoPath = saveLogoFile(logoFile, projectId);
            project.setLogoPath(savedLogoPath);
        }

        projectRepository.save(project);
        return true;
    }

    private String saveLogoFile(MultipartFile logoFile, int projectId) throws IOException {
        // Define the directory where you want to store the logo files
        String uploadDir = "Team_5_Phygital/src/main/resources/static/images";

        // Create the directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Extract the original file name and file extension
        String originalFileName = logoFile.getOriginalFilename();
        assert originalFileName != null;
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // Generate a unique file name for the logo file using the project ID
        String uniqueFileName = "project" + projectId + "-logo" + fileExtension;

        // Construct the path where the logo file will be saved
        Path filePath = Paths.get(uploadDir + File.separator + uniqueFileName);

        // Copy the logo file to the specified location
        Files.copy(logoFile.getInputStream(), filePath);

        // Return the saved path
        return uploadDir + "/" + uniqueFileName;
    }

    @Transactional
    public boolean deleteProject(int projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            return false;
        }
        projectRepository.deleteById(projectId);
        return true;
    }
}
