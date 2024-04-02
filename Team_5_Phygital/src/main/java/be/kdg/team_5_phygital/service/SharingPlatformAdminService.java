package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;
import be.kdg.team_5_phygital.repository.SharingPlatformAdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SharingPlatformAdminService{
    private SharingPlatformAdminRepository sharingPlatformAdminRepository;

    public SharingPlatformAdminService(SharingPlatformAdminRepository sharingPlatformAdminRepository) {
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

    @Transactional
    public SharingPlatformAdmin saveSharingPlatformAdmin(String name, String email, String password) {
        return sharingPlatformAdminRepository.save(new SharingPlatformAdmin(name, email, password));
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
