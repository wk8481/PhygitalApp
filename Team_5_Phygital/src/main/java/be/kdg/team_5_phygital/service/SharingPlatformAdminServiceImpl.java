package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;
import be.kdg.team_5_phygital.repository.SharingPlatformAdminRepo;

import java.util.List;
import java.util.Optional;

public class SharingPlatformAdminServiceImpl implements SharingPlatformAdminService{
    private SharingPlatformAdminRepo sharingPlatformAdminRepository;

    public SharingPlatformAdminServiceImpl(SharingPlatformAdminRepo sharingPlatformAdminRepository) {
        this.sharingPlatformAdminRepository = sharingPlatformAdminRepository;
    }

    @Override
    public SharingPlatformAdmin createSharingPlatformAdmin(SharingPlatformAdmin sharingPlatformAdmin) {
        return sharingPlatformAdminRepository.save(sharingPlatformAdmin);
    }

    @Override
    public Optional<SharingPlatformAdmin> findSharingPlatformAdminById(int id) {
        return sharingPlatformAdminRepository.findById(id);
    }

    @Override
    public List<SharingPlatformAdmin> findAllSharingPlatformAdmins() {
        return sharingPlatformAdminRepository.findAll();
    }
}
