package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;
import be.kdg.team_5_phygital.repository.SharingPlatformAdminRepository;
import be.kdg.team_5_phygital.repository.SharingPlatformRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SharingPlatformAdminService {
    private final SharingPlatformAdminRepository sharingPlatformAdminRepository;
    private final SharingPlatformRepository sharingPlatformRepository;

    public SharingPlatformAdminService(SharingPlatformAdminRepository sharingPlatformAdminRepository, SharingPlatformRepository sharingPlatformRepository) {
        this.sharingPlatformAdminRepository = sharingPlatformAdminRepository;
        this.sharingPlatformRepository = sharingPlatformRepository;
    }


    public SharingPlatformAdmin getSharingPlatformAdmin(int sharingPlatformAdminId) {
        return sharingPlatformAdminRepository.findById(sharingPlatformAdminId).orElse(null);
    }


    public List<SharingPlatformAdmin> getAllSharingPlatformAdmins() {
        return sharingPlatformAdminRepository.findAll();
    }

    @Transactional
    public SharingPlatformAdmin saveSharingPlatformAdmin(String name, String email, String password, int sharingPlatformId) {
        SharingPlatform sharingPlatform = sharingPlatformRepository.findById(sharingPlatformId).orElse(null);
        return sharingPlatformAdminRepository.save(new SharingPlatformAdmin(name, email, password, sharingPlatform));
    }

    public boolean updateSharingPlatformAdmin(int sharingPlatformId, String name, String email) {
        SharingPlatformAdmin sharingPlatformAdmin = sharingPlatformAdminRepository.findById(sharingPlatformId).orElse(null);
        if (sharingPlatformAdmin == null) {
            return false;
        }
        sharingPlatformAdmin.setName(name);
        sharingPlatformAdmin.setEmail(email);
        sharingPlatformAdminRepository.save(sharingPlatformAdmin);
        return true;
    }

    @Transactional
    public boolean deleteSharingPlatformAdmin(int sharingPlatformAdminId) {
        Optional<SharingPlatformAdmin> sharingPlatformAdmin = sharingPlatformAdminRepository.findById(sharingPlatformAdminId);
        if (sharingPlatformAdmin.isEmpty()) {
            return false;
        }
        sharingPlatformAdminRepository.deleteById(sharingPlatformAdminId);
        return true;
    }
}
