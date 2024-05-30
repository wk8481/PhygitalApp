package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.config.CSVUtils;
import be.kdg.team_5_phygital.controller.api.dto.NewSubThemeDto;
import be.kdg.team_5_phygital.controller.api.dto.SubThemeDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSubThemeDto;
import be.kdg.team_5_phygital.domain.Session;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.service.SessionService;
import be.kdg.team_5_phygital.service.SubThemeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.opencsv.CSVWriter;

@RestController
@RequestMapping("/api/sub-themes")
public class SubThemesController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SubThemeService subThemeService;
    private final ModelMapper modelMapper;
    private final SessionService sessionService;

    public SubThemesController(SubThemeService subThemeService, ModelMapper modelMapper, SessionService sessionService) {
        this.subThemeService = subThemeService;
        this.modelMapper = modelMapper;
        this.sessionService = sessionService;
    }

    @GetMapping("{id}")
    ResponseEntity<SubThemeDto> getSubTheme(@PathVariable("id") int subThemeId) {
        SubTheme subTheme = subThemeService.getSubTheme(subThemeId);
        if (subTheme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(subTheme, SubThemeDto.class));
    }

    @GetMapping
    ResponseEntity<List<SubThemeDto>> getAllSubThemes() {
        List<SubTheme> allSubThemes = subThemeService.getAllSubThemes();
        if (allSubThemes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<SubThemeDto> subThemeDtos = allSubThemes.stream().map(subTheme -> modelMapper.map(subTheme, SubThemeDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(subThemeDtos);
        }
    }

    @PostMapping
    ResponseEntity<SubThemeDto> saveSubTheme(@RequestBody @Valid NewSubThemeDto subThemeDto) {
        if (subThemeService.getSubThemeByName(subThemeDto.getName()) != null) {
            logger.error("Failed to create sub theme");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Saving new sub theme: {}", subThemeDto.getName());
        SubTheme createdSubTheme = subThemeService.saveSubTheme(subThemeDto.getName(), subThemeDto.getInformation(), subThemeDto.getFlowId());
        return new ResponseEntity<>(modelMapper.map(createdSubTheme, SubThemeDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{subThemeId}")
    ResponseEntity<Void> updateSubTheme(@PathVariable int subThemeId, @RequestBody UpdateSubThemeDto updateSubthemeDto) {
        if (subThemeService.updateSubTheme(subThemeId, updateSubthemeDto.getName(), updateSubthemeDto.getInformation())) {
            logger.info("Updating sub theme to: {}", updateSubthemeDto.getName());
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
        logger.error("Couldn't delete sub theme");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{subThemeId}/media")
    public ResponseEntity<?> uploadMediaFiles(@PathVariable("subThemeId") int subThemeId,
                                              @RequestParam("files") MultipartFile[] files) {
        try {
            subThemeService.uploadMediaFiles(subThemeId, Arrays.asList(files));
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{subThemeId}/media")
    public ResponseEntity<?> deleteMediaFiles(@PathVariable("subThemeId") int subThemeId,
                                              @RequestParam("fileNames") List<String> fileNames) {
        try {
            subThemeService.deleteMediaFiles(subThemeId, fileNames);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/session/{id}/download-csv")
    public ResponseEntity<InputStreamResource> downloadCSV(@PathVariable("id") int sessionId) throws IOException {
        List <Session> sessionList = new ArrayList<>();
        sessionList.add(sessionService.getSession(sessionId));
        Session sessions = sessionService.getAnswersAndQuestionsOfSession(sessionList).get(0);

        String filePath = "session.csv";
        CSVUtils.writeSessionToCSV(sessions, filePath);

        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.parseMediaType("application/csv"))
                .contentLength(file.length())
                .body(resource);
    }
}
