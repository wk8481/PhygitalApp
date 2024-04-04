package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.QuestionDto;
import be.kdg.team_5_phygital.controller.api.dto.NewQuestionDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateQuestionDto;
import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.service.FlowService;
import be.kdg.team_5_phygital.service.QuestionService;
import be.kdg.team_5_phygital.service.SubThemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/installation")
public class InstallationApiController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final QuestionService questionService;
    private final FlowService flowService;
    private final SubThemeService subThemeService;

    public InstallationApiController(QuestionService questionService, FlowService flowService, SubThemeService subThemeService) {
        this.questionService = questionService;
        this.flowService = flowService;
        this.subThemeService = subThemeService;
    }


    // Get all questions for a specific SubTheme ID
    @GetMapping("/questions/{subThemeId}")
    public ResponseEntity<List<QuestionDto>> getAllQuestionsForSubTheme(@PathVariable int subThemeId) {
        Optional<SubTheme> subTheme = subThemeService.getSubThemeById(subThemeId);
        if (subTheme.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Question> questions = questionService.getQuestionBySubTheme(subTheme.get());
        List<QuestionDto> questionDtos = questions.stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok(questionDtos);
    }


    // Create a new question for a specific SubTheme ID
    @PostMapping("/questions/{subThemeId}")
    public ResponseEntity<QuestionDto> createQuestionForSubTheme(@PathVariable int subThemeId, @RequestBody NewQuestionDto newQuestionDto) {
        Optional<SubTheme> subTheme = subThemeService.getSubThemeById(subThemeId);
        if (subTheme.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Question question = convertToEntity(newQuestionDto);
        question.setSubTheme(subTheme.get());
        Question createdQuestion = questionService.addQuestion(question);
        QuestionDto createdQuestionDto = new QuestionDto(createdQuestion.getId(), createdQuestion.getText(), createdQuestion.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestionDto);
    }

    @PostMapping("/questions/submit")
    public ResponseEntity<String> submitAnswer(@RequestBody NewQuestionDto newQuestionDto) {
        Question question = questionService.addQuestion(convertToEntity(newQuestionDto));
        if (question != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Answer submitted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to submit answer.");
        }
    }

    // Get the current question for a specific SubTheme ID
    @GetMapping("/questions/current/{subThemeId}")
    public ResponseEntity<QuestionDto> getCurrentQuestion(@PathVariable int subThemeId) {
        return subThemeService.getSubThemeById(subThemeId)
                .map(SubTheme::getCurrentIndex)
                .flatMap(currentIndex -> questionService.getCurrentQuestion(subThemeId, currentIndex))
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/questions/next/{subThemeId}")
    public ResponseEntity<QuestionDto> getNextQuestion(@PathVariable int subThemeId) {
        Optional<SubTheme> subThemeOptional = subThemeService.getSubThemeById(subThemeId);
        if (subThemeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SubTheme subTheme = subThemeOptional.get();
        int currentIndex = subTheme.getCurrentIndex();
        boolean isCircular = subTheme.getFlow().isCircular();  // Assuming there's a getFlow() method in SubTheme

        return questionService.getNextQuestion(subThemeId, currentIndex, isCircular)
                .map(question -> ResponseEntity.ok(convertToDto(question)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/questions/previous/{subThemeId}")
    public ResponseEntity<QuestionDto> getPreviousQuestion(@PathVariable int subThemeId) {
        Optional<SubTheme> subThemeOptional = subThemeService.getSubThemeById(subThemeId);
        if (subThemeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SubTheme subTheme = subThemeOptional.get();
        int currentIndex = subTheme.getCurrentIndex();
        boolean isCircular = subTheme.getFlow().isCircular();  // Assuming there's a getFlow() method in SubTheme

        return questionService.getPreviousQuestion(subThemeId, currentIndex, isCircular)
                .map(question -> ResponseEntity.ok(convertToDto(question)))
                .orElse(ResponseEntity.notFound().build());
    }




    // Delete a question
    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable int questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }

    // Helper method for conversion to DTO
    private QuestionDto convertToDto(Question question) {
        return new QuestionDto(question.getId(), question.getText(), question.getType());
    }

    // Helper method for conversion from NewQuestionDto to Entity
    private Question convertToEntity(NewQuestionDto newQuestionDto) {
        return new Question(newQuestionDto.getText(), newQuestionDto.getType());
    }
}
