package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.repository.SharingPlatformRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Sharing platform service.
 */
@Service
public class SharingPlatformService {
    private final SharingPlatformRepository sharingPlatformRepository;

    /**
     * Instantiates a new Sharing platform service.
     *
     * @param sharingPlatformRepository the sharing platform repository
     */
    public SharingPlatformService(SharingPlatformRepository sharingPlatformRepository) {
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
     * Save sharing platform sharing platform.
     *
     * @param name         the name
     * @param contactEmail the contact email
     * @return the sharing platform
     */
    @Transactional
    public SharingPlatform saveSharingPlatform(String name, String contactEmail) {
        return sharingPlatformRepository.save(new SharingPlatform(name, contactEmail));
    }

    /**
     * Update sharing platform boolean.
     *
     * @param sharingPlatformId the sharing platform id
     * @param name              the name
     * @return the boolean
     */
    public boolean updateSharingPlatform(int sharingPlatformId, String name) {
        SharingPlatform sharingPlatform = sharingPlatformRepository.findById(sharingPlatformId).orElse(null);
        if (sharingPlatform == null) {
            return false;
        }
        sharingPlatform.setName(name);
        sharingPlatformRepository.save(sharingPlatform);
        return true;
    }
}
