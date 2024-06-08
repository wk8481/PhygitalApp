package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.controller.api.dto.UpdateSharingPlatformDto;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.Client;
import be.kdg.team_5_phygital.repository.ClientRepository;
import be.kdg.team_5_phygital.repository.SharingPlatformRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * The type Sharing platform service.
 */
@Service
public class SharingPlatformService {
    private final SharingPlatformRepository sharingPlatformRepository;
    private final ClientRepository clientRepository;
    private final ProjectService projectService;

    /**
     * Instantiates a new Sharing platform service.
     *
     * @param sharingPlatformRepository      the sharing platform repository
     * @param clientRepository the sharing platform admin repository
     */
    public SharingPlatformService(SharingPlatformRepository sharingPlatformRepository, ClientRepository clientRepository, ProjectService projectService) {
        this.sharingPlatformRepository = sharingPlatformRepository;
        this.clientRepository = clientRepository;
        this.projectService = projectService;
    }


    /**
     * Gets sharing platform.
     *
     * @param id the id
     * @return the sharing platform
     */
    public SharingPlatform getSharingPlatform(int id) {
        return sharingPlatformRepository.findById(id).orElse(null);
    }

    /**
     * Gets sharing platform by name.
     *
     * @param name the name
     * @return the sharing platform by name
     */
    public SharingPlatform getSharingPlatformByName(String name) {
        return sharingPlatformRepository.findByName(name).orElse(null);
    }

    /**
     * Gets all sharing platforms.
     *
     * @return the all sharing platforms
     */
    public List<SharingPlatform> getAllSharingPlatforms() {
        return sharingPlatformRepository.findAll();
    }

    @Transactional
    public SharingPlatform saveSharingPlatform(String name, String contactEmail, String information) {
        SharingPlatform sharingPlatform = new SharingPlatform(name, contactEmail, information);
        clientRepository.save(new Client("Unnamed Client", "default@email.com", "password", sharingPlatform));
        return sharingPlatformRepository.save(sharingPlatform);
    }

    public boolean updateSharingPlatform(int sharingPlatformId, UpdateSharingPlatformDto updateSharingPlatformDto, MultipartFile logoFile, String information) throws IOException {
        SharingPlatform sharingPlatform = sharingPlatformRepository.findById(sharingPlatformId).orElse(null);
        if (sharingPlatform == null) {
            return false;
        }
        sharingPlatform.setName(updateSharingPlatformDto.getName());
        sharingPlatform.setContactEmail(updateSharingPlatformDto.getContactEmail());
        sharingPlatform.setInformation(information);

        // Handle logo file upload
        if (logoFile != null && !logoFile.isEmpty()) {
            // Save the logo file and update the logoPath
            String savedLogoPath = saveLogoFile(logoFile, sharingPlatformId);
            sharingPlatform.setLogoPath(savedLogoPath);
        }

        sharingPlatformRepository.save(sharingPlatform);
        return true;
    }

    private String saveLogoFile(MultipartFile logoFile, int sharingPlatformId) throws IOException {
        // Define the directory where you want to store the logo files
        String uploadDir = "src/main/resources/static/images";

        // Create the directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Extract the original file name and file extension
        String originalFileName = logoFile.getOriginalFilename();
        assert originalFileName != null;
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // Generate a unique file name for the logo file using the platform ID
        String uniqueFileName = "platform" + sharingPlatformId + "-logo" + fileExtension;

        // Construct the path where the logo file will be saved
        Path filePath = Paths.get(uploadDir + File.separator + uniqueFileName);

        // Copy the logo file to the specified location
        Files.copy(logoFile.getInputStream(), filePath);

        // Return the saved path
        return uploadDir + "/" + uniqueFileName;
    }

    /**
     * Delete sharing platform boolean.
     *
     * @param sharingPlatformId the sharing platform id
     * @return the boolean
     */
    @Transactional
    public boolean deleteSharingPlatform(int sharingPlatformId) {
        Optional<SharingPlatform> sharingPlatform = sharingPlatformRepository.findById(sharingPlatformId);
        if (sharingPlatform.isEmpty()) {
            return false;
        }
        for (Project project : projectService.getProjectBySharingPlatformId(sharingPlatform.get().getId())) {
            projectService.deleteProject(project.getId());
        }
        clientRepository.deleteAll(getSharingPlatform(sharingPlatformId).getSharingPlatformAdmin());

        sharingPlatformRepository.deleteById(sharingPlatformId);
        return true;
    }

    public void updateTimeAndParticipants(SharingPlatform sharingPlatform, float durationSpend) {

        sharingPlatformRepository.updatePlatformTimeAndParticipants(sharingPlatform.getId(), durationSpend);
    }
}
