package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.repository.SharingPlatformRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Sharing platform service.
 */
@Service
public class SharingPlatformService {
    private final SharingPlatformRepo sharingPlatformRepository;

    /**
     * Instantiates a new Sharing platform service.
     *
     * @param sharingPlatformRepository the sharing platform repo
     */
    public SharingPlatformService(SharingPlatformRepo sharingPlatformRepository) {
        this.sharingPlatformRepository = sharingPlatformRepository;
    }


    /**
     * Create sharing platform sharing platform.
     *
     * @param sharingPlatform the sharing platform
     * @return the sharing platform
     */
    public SharingPlatform createSharingPlatform(SharingPlatform sharingPlatform) {
        return sharingPlatformRepository.save(sharingPlatform);
    }


    /**
     * Gets sharing platform by id.
     *
     * @param id the id
     * @return the sharing platform by id
     */
    public Optional<SharingPlatform> getSharingPlatformById(int id) {
        return sharingPlatformRepository.findById(id);
    }


    /**
     * Gets all sharing platforms.
     *
     * @return the all sharing platforms
     */
    public List<SharingPlatform> getAllSharingPlatforms() {
        return sharingPlatformRepository.findAll();
    }

    /**
     * Update sharing platform boolean.
     *
     * @param sharingPlatformId the sharing platform id
     * @param name              the name
     * @return the boolean
     */
    public boolean updateSharingPlatform(int sharingPlatformId, String name) {
        SharingPlatform sharingPlatformAdmin = sharingPlatformRepository.findById(sharingPlatformId).orElse(null);
        if (sharingPlatformAdmin == null) {
            return false;
        }
        sharingPlatformAdmin.setName(name);
        sharingPlatformRepository.save(sharingPlatformAdmin);
        return true;
    }
}
