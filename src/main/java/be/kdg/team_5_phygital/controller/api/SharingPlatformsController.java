package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewSharingPlatformDto;
import be.kdg.team_5_phygital.controller.api.dto.SharingPlatformDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSharingPlatformDto;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.service.SharingPlatformService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sharing-platforms")
public class SharingPlatformsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SharingPlatformService sharingPlatformService;
    private final ModelMapper modelMapper;

    public SharingPlatformsController(SharingPlatformService sharingPlatformService, ModelMapper modelMapper) {
        this.sharingPlatformService = sharingPlatformService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    ResponseEntity<SharingPlatformDto> getSharingPlatform(@PathVariable("id") int sharingPlatformId) {
        SharingPlatform sharingPlatform = sharingPlatformService.getSharingPlatform(sharingPlatformId);
        if (sharingPlatform == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(sharingPlatform, SharingPlatformDto.class));
    }

    @GetMapping
    ResponseEntity<List<SharingPlatformDto>> getAllSharingPlatforms() {
        List<SharingPlatform> allSharingPlatforms = sharingPlatformService.getAllSharingPlatforms();
        if (allSharingPlatforms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<SharingPlatformDto> sharingPlatformDtos = allSharingPlatforms.stream().map(sharingPlatform -> modelMapper.map(sharingPlatform, SharingPlatformDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(sharingPlatformDtos);
        }
    }

    @PostMapping
    ResponseEntity<SharingPlatformDto> saveSharingPlatform(@RequestBody @Valid NewSharingPlatformDto sharingPlatformDto) {
        if (sharingPlatformService.getSharingPlatformByName(sharingPlatformDto.getName()) != null) {
            logger.error("Could not create new sharing platform");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Creating new sharing platform: {}", sharingPlatformDto.getName());
        SharingPlatform createdSharingPlatform = sharingPlatformService.saveSharingPlatform(sharingPlatformDto.getName(), sharingPlatformDto.getContactEmail());
        return new ResponseEntity<>(modelMapper.map(createdSharingPlatform, SharingPlatformDto.class), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{sharingPlatformId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateSharingPlatform(
            @PathVariable int sharingPlatformId,
            @RequestPart("updateSharingPlatformDto") UpdateSharingPlatformDto updateSharingPlatformDto,
            @RequestPart(value = "logo", required = false) MultipartFile logoFile) throws IOException {

        if (sharingPlatformService.updateSharingPlatform(sharingPlatformId, updateSharingPlatformDto, logoFile)) {
            logger.info("Updating sharing platform to: {}", updateSharingPlatformDto.getName());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("{platformId}")
    ResponseEntity<Void> deleteSharingPlatform(@PathVariable("platformId") int sharingPlatformId) {
        if (sharingPlatformService.deleteSharingPlatform(sharingPlatformId)) {
            logger.info("Deleting sharing platform");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.error("Could not delete sharing platform");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
