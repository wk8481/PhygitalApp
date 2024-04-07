package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewSubThemeDto;
import be.kdg.team_5_phygital.controller.api.dto.SubThemeDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSubThemeDto;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.repository.SubThemeRepository;
import be.kdg.team_5_phygital.service.SubThemeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sub-themes")
public class SubThemesController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SubThemeService subThemeService;
    private final SubThemeRepository subThemeRepository;
    private final ModelMapper modelMapper;

    public SubThemesController(SubThemeService subThemeService, SubThemeRepository subThemeRepository, ModelMapper modelMapper) {
        this.subThemeService = subThemeService;
        this.subThemeRepository = subThemeRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    ResponseEntity<SubThemeDto> saveSubTheme(@RequestBody @Valid NewSubThemeDto subThemeDto) {
        if (subThemeRepository.findByName(subThemeDto.getName()).isPresent()) {
            logger.error("failed to create sub theme");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Saving new sub theme: "+ subThemeDto);
        SubTheme createdSubTheme = subThemeService.saveSubTheme(subThemeDto.getName(), subThemeDto.getInformation(), subThemeDto.getFlowId());
        return new ResponseEntity<>(modelMapper.map(createdSubTheme, SubThemeDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{subThemeId}")
    ResponseEntity<Void> updateSubtheme(@PathVariable int subThemeId, @RequestBody UpdateSubThemeDto updateSubthemeDto) {
        if (subThemeService.updateSubTheme(subThemeId, updateSubthemeDto.getName(), updateSubthemeDto.getInformation())) {
            logger.info("Updating sub theme to: "+updateSubthemeDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{subThemeId}")
    ResponseEntity<Void> deleteSubTheme(@PathVariable("subThemeId") int subThemeId) {
        if (subThemeService.deleteSubTheme(subThemeId)) {
            logger.info("Deleting sub theme");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.error("Couldnt delete sub theme");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
