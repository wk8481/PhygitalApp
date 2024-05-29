package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.Client;
import be.kdg.team_5_phygital.repository.ClientRepository;
import be.kdg.team_5_phygital.repository.SharingPlatformRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final SharingPlatformRepository sharingPlatformRepository;

    public ClientService(ClientRepository clientRepository, SharingPlatformRepository sharingPlatformRepository) {
        this.clientRepository = clientRepository;
        this.sharingPlatformRepository = sharingPlatformRepository;
    }


    public Client getSharingPlatformAdmin(int sharingPlatformAdminId) {
        return clientRepository.findById(sharingPlatformAdminId).orElse(null);
    }


    public List<Client> getAllSharingPlatformAdmins() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client saveSharingPlatformAdmin(String name, String email, String password, int sharingPlatformId) {
        SharingPlatform sharingPlatform = sharingPlatformRepository.findById(sharingPlatformId).orElse(null);
        return clientRepository.save(new Client(name, email, password, sharingPlatform));
    }

    public boolean updateSharingPlatformAdmin(int sharingPlatformId, String name, String email) {
        Client client = clientRepository.findById(sharingPlatformId).orElse(null);
        if (client == null) {
            return false;
        }
        client.setName(name);
        client.setEmail(email);
        clientRepository.save(client);
        return true;
    }

    @Transactional
    public boolean deleteSharingPlatformAdmin(int sharingPlatformAdminId) {
        Optional<Client> sharingPlatformAdmin = clientRepository.findById(sharingPlatformAdminId);
        if (sharingPlatformAdmin.isEmpty()) {
            return false;
        }
        clientRepository.deleteById(sharingPlatformAdminId);
        return true;
    }
}
