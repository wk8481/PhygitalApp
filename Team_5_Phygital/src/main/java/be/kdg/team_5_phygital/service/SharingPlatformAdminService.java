package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;

import java.util.List;
import java.util.Optional;

public interface SharingPlatformAdminService {

    SharingPlatformAdmin createSharingPlatformAdmin(SharingPlatformAdmin sharingPlatformAdmin);

    Optional<SharingPlatformAdmin> findSharingPlatformAdminById(int id);

    List<SharingPlatformAdmin> findAllSharingPlatformAdmins();
}
