package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;
import be.kdg.team_5_phygital.repository.SharingPlatformAdminRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SharingPlatformAdminService{
    private SharingPlatformAdminRepo sharingPlatformAdminRepository;

    public SharingPlatformAdminService(SharingPlatformAdminRepo sharingPlatformAdminRepository) {
        this.sharingPlatformAdminRepository = sharingPlatformAdminRepository;
    }


    public SharingPlatformAdmin createSharingPlatformAdmin(SharingPlatformAdmin sharingPlatformAdmin) {
        return sharingPlatformAdminRepository.save(sharingPlatformAdmin);
    }


    public Optional<SharingPlatformAdmin> findSharingPlatformAdminById(int id) {
        return sharingPlatformAdminRepository.findById(id);
    }


    public List<SharingPlatformAdmin> findAllSharingPlatformAdmins() {
        return sharingPlatformAdminRepository.findAll();
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
}
