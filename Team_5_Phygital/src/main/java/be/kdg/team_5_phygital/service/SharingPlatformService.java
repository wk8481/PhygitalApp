package be.kdg.team_5_phygital.service;

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
    public boolean updateSharingPlatform(int sharingPlatformId, String name, String contactEmail) {
        SharingPlatform sharingPlatform = sharingPlatformRepository.findById(sharingPlatformId).orElse(null);
        if (sharingPlatform == null) {
            return false;
        }
        sharingPlatform.setName(name);
        sharingPlatform.setContactEmail(contactEmail);
        sharingPlatformRepository.save(sharingPlatform);
        return true;
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
        sharingPlatformRepository.deleteById(sharingPlatformId);
        return true;
    }
}
