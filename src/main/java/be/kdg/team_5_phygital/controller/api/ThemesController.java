package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.ThemeDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateThemeDto;
import be.kdg.team_5_phygital.domain.Theme;
import be.kdg.team_5_phygital.service.ThemeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/themes")
public class ThemesController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ThemeService themeService;
    private final ModelMapper modelMapper;

    public ThemesController(ThemeService themeService, ModelMapper modelMapper) {
        this.themeService = themeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    ResponseEntity<ThemeDto> getTheme(@PathVariable("id") int themeId) {
        Theme theme = themeService.getTheme(themeId);
        if (theme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(theme, ThemeDto.class));
    }

    @GetMapping
    ResponseEntity<List<ThemeDto>> getAllThemes() {
        List<Theme> allThemes = themeService.getAllThemes();
        if (allThemes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<ThemeDto> themeDtos = allThemes.stream().map(theme -> modelMapper.map(theme, ThemeDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(themeDtos);
        }
    }

    @PatchMapping("{projectId}")
    ResponseEntity<Void> updateTheme(@PathVariable int projectId, @RequestBody UpdateThemeDto updateThemeDto) {
        if (themeService.updateTheme(themeService.getThemeByProjectId(projectId).getId(), updateThemeDto.getName())) {
            logger.info("Updating theme to: {}", updateThemeDto.getName());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
