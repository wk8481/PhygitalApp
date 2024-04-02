package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.*;
import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.repository.*;
import be.kdg.team_5_phygital.service.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sharing-platform")
public class SharingPlatformApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjectService projectService;
    private final SupervisorService supervisorService;
    private final ThemeService themeService;
    private final SubThemeService subThemeService;
    private final FlowService flowService;
    private final QuestionService questionService;
    private final ProjectRepository projectRepository;
    private final SupervisorRepository supervisorRepository;
    private final ThemeRepository themeRepository;
    private final SubThemeRepository subThemeRepository;
    private final FlowRepository flowRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public SharingPlatformApiController(ProjectService projectService, SupervisorService supervisorService, ThemeService themeService, SubThemeService subThemeService, FlowService flowService, QuestionService questionService, ProjectRepository projectRepository, SupervisorRepository supervisorRepository, ThemeRepository themeRepository, SubThemeRepository subThemeRepository, FlowRepository flowRepository, QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.projectService = projectService;
        this.supervisorService = supervisorService;
        this.themeService = themeService;
        this.subThemeService = subThemeService;
        this.flowService = flowService;
        this.questionService = questionService;
        this.projectRepository = projectRepository;
        this.supervisorRepository = supervisorRepository;
        this.themeRepository = themeRepository;
        this.subThemeRepository = subThemeRepository;
        this.flowRepository = flowRepository;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/platform/{platformId}/project/{projectId}")
    ResponseEntity<ProjectDto> saveProject(@PathVariable int platformId, @PathVariable int projectId, @RequestBody @Valid NewProjectDto projectDto) {
        if (projectRepository.findByName(projectDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Project createdProject = projectService.saveProject(projectDto.getName());
        return new ResponseEntity<>(modelMapper.map(createdProject, ProjectDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/platform/{platformId}/supervisor/{supervisorId}")
    ResponseEntity<SupervisorDto> saveSupervisor(@PathVariable int supervisorId, @RequestBody @Valid NewSupervisorDto supervisorDto) {
        if (supervisorRepository.findByName(supervisorDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Supervisor createdSupervisor = supervisorService.saveSupervisor(supervisorDto.getName(), supervisorDto.getEmail());
        return new ResponseEntity<>(modelMapper.map(createdSupervisor, SupervisorDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/platform/{platformId}/project/{projectId}/theme")
    ResponseEntity<ThemeDto> saveTheme(@PathVariable int platformId, @PathVariable int projectId, @RequestBody @Valid NewThemeDto themeDto) {
        if (themeRepository.findByName(themeDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Theme createdTheme = themeService.saveTheme(themeDto.getName(), themeDto.getInformation());
        return new ResponseEntity<>(modelMapper.map(createdTheme, ThemeDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/platform/{platformId}/flow/{flowId}")
    ResponseEntity<FlowDto> saveFlow(@PathVariable int flowId, @RequestBody @Valid NewFlowDto flowDto) {
        if (flowRepository.findByName(flowDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Flow createdFlow = flowService.saveFlow(flowDto.getName());
        return new ResponseEntity<>(modelMapper.map(createdFlow, FlowDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/flow/{flowId}/sub-theme/{subThemeId}")
    ResponseEntity<SubThemeDto> saveSubTheme(@PathVariable int subThemeId, @RequestBody @Valid NewSubThemeDto subThemeDto) {
        if (subThemeRepository.findByName(subThemeDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        SubTheme createdSubTheme = subThemeService.saveSubTheme(subThemeDto.getName(), subThemeDto.getInformation());
        return new ResponseEntity<>(modelMapper.map(createdSubTheme, SubThemeDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/sub-theme/{stId}/question/{questionId}")
    ResponseEntity<QuestionDto> saveQuestion(@PathVariable int questionId, @RequestBody @Valid NewQuestionDto questionDto) {
        if (questionRepository.findByText(questionDto.getText()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Question createdQuestion = questionService.saveQuestion(questionDto.getText(), questionDto.getType());
        return new ResponseEntity<>(modelMapper.map(createdQuestion, QuestionDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("/platform/{platformId}/project/{projectId}/update")
    ResponseEntity<Void> updateProject(@PathVariable int platformId, @PathVariable int projectId, @RequestBody UpdateProjectDto updateProject) {
        if (projectService.updateProject(projectId, updateProject.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/platform/{platformId}/supervisor/{supervisorId}/update")
    ResponseEntity<Void> updateSupervisorName(@PathVariable int supervisorId, @RequestBody UpdateSupervisorDto updateSupervisorDto) {
        if (supervisorService.updateSupervisor(supervisorId, updateSupervisorDto.getName(), updateSupervisorDto.getEmail())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/platform/{platformId}/project/{projectId}/theme/update")
    ResponseEntity<Void> updateTheme(@PathVariable int platformId, @PathVariable int projectId, @RequestBody UpdateThemeDto updateTheme) {
        if (themeService.updateTheme(themeService.getThemeByProjectId(projectId).getId(), updateTheme.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/platform/{platformId}/flow/{flowId}/update")
    ResponseEntity<Void> updateFlow(@PathVariable int flowId, @RequestBody UpdateFlowDto updateFlowDto) {
        if (flowService.updateFlow(flowId, updateFlowDto.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/flow/{flowId}/sub-theme/{subThemeId}/update")
    ResponseEntity<Void> updateSubtheme(@PathVariable int subThemeId, @RequestBody UpdateSubThemeDto updateSubthemeDto) {
        if (subThemeService.updateSubTheme(subThemeId, updateSubthemeDto.getName(), updateSubthemeDto.getInformation())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/sub-theme/{stId}/question/{questionId}/update")
    ResponseEntity<Void> updateQuestion(@PathVariable int questionId, @RequestBody UpdateQuestionDto updateQuestionDto) {
        if (questionService.updateQuestion(questionId, updateQuestionDto.getText(), updateQuestionDto.getType())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
