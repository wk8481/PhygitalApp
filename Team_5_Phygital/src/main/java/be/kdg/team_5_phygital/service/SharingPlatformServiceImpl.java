package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;
import be.kdg.team_5_phygital.repository.SharingPlatformRepo;

import java.util.List;
import java.util.Optional;

public class SharingPlatformServiceImpl implements SharingPlatformService{
    private SharingPlatformRepo sharingPlatformRepo;


    @Override
    public SharingPlatform createSharingPlatform(SharingPlatform sharingPlatform) {
        return sharingPlatformRepo.save(sharingPlatform);
    }

    @Override
    public Optional<SharingPlatform> getSharingPlatformById(int id) {
        return sharingPlatformRepo.findById(id);
    }

    @Override
    public List<SharingPlatform> getAllSharingPlatforms() {
        return sharingPlatformRepo.findAll();
    }
}
