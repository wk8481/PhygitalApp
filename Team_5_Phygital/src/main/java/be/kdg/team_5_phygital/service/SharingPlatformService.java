package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.repository.SharingPlatformRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SharingPlatformService {
    private final SharingPlatformRepo sharingPlatformRepo;

    public SharingPlatformService(SharingPlatformRepo sharingPlatformRepo) {
        this.sharingPlatformRepo = sharingPlatformRepo;
    }


    public SharingPlatform createSharingPlatform(SharingPlatform sharingPlatform) {
        return sharingPlatformRepo.save(sharingPlatform);
    }


    public Optional<SharingPlatform> getSharingPlatformById(int id) {
        return sharingPlatformRepo.findById(id);
    }


    public List<SharingPlatform> getAllSharingPlatforms() {
        return sharingPlatformRepo.findAll();
    }

    public SharingPlatform updateSharingPlatform(SharingPlatform updatedSharingPlatform){
        return sharingPlatformRepo.save(updatedSharingPlatform);
    }
}
