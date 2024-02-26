package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;

import java.util.List;
import java.util.Optional;

public interface SharingPlatformService {

    SharingPlatform createSharingPlatform(SharingPlatform sharingPlatform);

    Optional<SharingPlatform> getSharingPlatformById(int id);

    List<SharingPlatform> getAllSharingPlatforms();

}
