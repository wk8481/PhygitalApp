package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewSharingPlatformDto;
import be.kdg.team_5_phygital.controller.api.dto.SharingPlatformDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSharingPlatformDto;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.repository.SharingPlatformRepository;
import be.kdg.team_5_phygital.service.SharingPlatformService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sharing-platforms")
public class SharingPlatformsController {
    private final SharingPlatformService sharingPlatformService;
    private final SharingPlatformRepository sharingPlatformRepository;
    private final ModelMapper modelMapper;

    public SharingPlatformsController(SharingPlatformService sharingPlatformService, SharingPlatformRepository sharingPlatformRepository, ModelMapper modelMapper) {
        this.sharingPlatformService = sharingPlatformService;
        this.sharingPlatformRepository = sharingPlatformRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    ResponseEntity<SharingPlatformDto> saveSharingPlatform(@RequestBody @Valid NewSharingPlatformDto sharingPlatformDto) {
        if (sharingPlatformRepository.findByName(sharingPlatformDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        SharingPlatform createdSharingPlatform = sharingPlatformService.saveSharingPlatform(sharingPlatformDto.getName(), sharingPlatformDto.getContactEmail());
        return new ResponseEntity<>(modelMapper.map(createdSharingPlatform, SharingPlatformDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{platformId}")
    ResponseEntity<Void> updateSharingPlatform(@PathVariable int platformId, @RequestBody UpdateSharingPlatformDto updateSharingPlatform) {
        if (sharingPlatformService.updateSharingPlatform(platformId, updateSharingPlatform.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{platformId}")
    ResponseEntity<Void> deleteSharingPlatform(@PathVariable("platformId") int sharingPlatformId) {
        if (sharingPlatformService.deleteSharingPlatform(sharingPlatformId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
