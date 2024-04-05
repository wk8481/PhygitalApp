package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewThemeDto;
import be.kdg.team_5_phygital.controller.api.dto.ThemeDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateThemeDto;
import be.kdg.team_5_phygital.domain.Theme;
import be.kdg.team_5_phygital.repository.ThemeRepository;
import be.kdg.team_5_phygital.service.ThemeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/themes")
public class ThemesController {
    private final ThemeService themeService;
    private final ThemeRepository themeRepository;
    private final ModelMapper modelMapper;

    public ThemesController(ThemeService themeService, ThemeRepository themeRepository, ModelMapper modelMapper) {
        this.themeService = themeService;
        this.themeRepository = themeRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    ResponseEntity<ThemeDto> saveTheme(@RequestBody @Valid NewThemeDto themeDto) {
        if (themeRepository.findByName(themeDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Theme createdTheme = themeService.saveTheme(themeDto.getName(), themeDto.getInformation(), themeDto.getProjectId());
        return new ResponseEntity<>(modelMapper.map(createdTheme, ThemeDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{projectId}")
    ResponseEntity<Void> updateTheme(@PathVariable int projectId, @RequestBody UpdateThemeDto updateTheme) {
        if (themeService.updateTheme(themeService.getThemeByProjectId(projectId).getId(), updateTheme.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{projectId}")
    ResponseEntity<Void> deleteTheme(@PathVariable("projectId") int projectId) {
        if (themeService.deleteTheme(themeService.getThemeByProjectId(projectId).getId())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
