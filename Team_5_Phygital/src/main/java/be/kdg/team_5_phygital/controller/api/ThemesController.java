package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.UpdateThemeDto;
import be.kdg.team_5_phygital.service.ThemeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/themes")
public class ThemesController {
    private final ThemeService themeService;

    public ThemesController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PatchMapping("{projectId}")
    ResponseEntity<Void> updateTheme(@PathVariable int projectId, @RequestBody UpdateThemeDto updateTheme) {
        if (themeService.updateTheme(themeService.getThemeByProjectId(projectId).getId(), updateTheme.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
