package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewQuestionDto;
import be.kdg.team_5_phygital.controller.api.dto.QuestionDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateQuestionDto;
import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.service.QuestionService;
import be.kdg.team_5_phygital.service.SubThemeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final QuestionService questionService;
    private final SubThemeService subThemeService;
    private final ModelMapper modelMapper;

    public QuestionsController(QuestionService questionService, SubThemeService subThemeService, ModelMapper modelMapper) {
        this.questionService = questionService;
        this.subThemeService = subThemeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    ResponseEntity<QuestionDto> getQuestion(@PathVariable("id") int questionId) {
        Question question = questionService.getQuestion(questionId);
        if (question == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(question, QuestionDto.class));
    }

    @GetMapping
    ResponseEntity<List<QuestionDto>> getAllQuestions() {
        List<Question> allQuestions = questionService.getAllQuestions();
        if (allQuestions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<QuestionDto> questionDtos = allQuestions.stream().map(question -> modelMapper.map(question, QuestionDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(questionDtos);
        }
    }

    @GetMapping("{subThemeId}")
    public ResponseEntity<List<QuestionDto>> getAllQuestionsBySubTheme(@PathVariable int subThemeId) {
        SubTheme subTheme = subThemeService.getSubThemeById(subThemeId).orElse(null);
        List<Question> allQuestions = questionService.getQuestionsBySubTheme(subTheme);
        List<QuestionDto> questionDtos = allQuestions.stream().map(question -> modelMapper.map(question, QuestionDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(questionDtos);
    }

    @GetMapping("{subThemeId}/current")
    public ResponseEntity<QuestionDto> getCurrentQuestion(@PathVariable int subThemeId) {
        return subThemeService.getSubThemeById(subThemeId)
                .map(SubTheme::getCurrentIndex)
                .flatMap(currentIndex -> questionService.getCurrentQuestion(subThemeId, currentIndex))
                .map(question -> modelMapper.map(question, QuestionDto.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("{subThemeId}/next")
    public ResponseEntity<QuestionDto> getNextQuestion(@PathVariable int subThemeId) {
        SubTheme subTheme = subThemeService.getSubThemeById(subThemeId).orElse(null);

        if (subTheme == null) {
            return ResponseEntity.notFound().build();
        }
        int currentIndex = subTheme.getCurrentIndex();
        boolean isCircular = subTheme.getFlow().isCircular();

        return questionService.getNextQuestion(subThemeId, currentIndex, isCircular)
                .map(question -> modelMapper.map(question, QuestionDto.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("{subThemeId}/previous")
    public ResponseEntity<QuestionDto> getPreviousQuestion(@PathVariable int subThemeId) {
        SubTheme subTheme = subThemeService.getSubThemeById(subThemeId).orElse(null);
        if (subTheme == null) {
            return ResponseEntity.notFound().build();
        }

        int currentIndex = subTheme.getCurrentIndex();
        boolean isCircular = subTheme.getFlow().isCircular();

        return questionService.getPreviousQuestion(subThemeId, currentIndex, isCircular)
                .map(question -> modelMapper.map(question, QuestionDto.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<QuestionDto> saveQuestion(@RequestBody @Valid NewQuestionDto questionDto) {
        if (questionService.getQuestionByText(questionDto.getText()) != null) {
            logger.error("Could not create new question");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Creating new question: {}", questionDto.getText());
        Question createdQuestion = questionService.saveQuestion(questionDto.getText(), questionDto.getType(), questionDto.getSubThemeId());
        return new ResponseEntity<>(modelMapper.map(createdQuestion, QuestionDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/questions/submit")
    public ResponseEntity<String> submitAnswer(@RequestBody NewQuestionDto newQuestionDto) {
        Question question = modelMapper.map(newQuestionDto, Question.class);
        if (question != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Answer submitted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to submit answer.");
        }
    }

    @PatchMapping("{questionId}")
    ResponseEntity<Void> updateQuestion(@PathVariable int questionId, @RequestBody UpdateQuestionDto updateQuestionDto) {
        if (questionService.updateQuestion(questionId, updateQuestionDto.getText(), updateQuestionDto.getType())) {
            logger.info("Updating question to: {}", updateQuestionDto.getText());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("Could not find question");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{questionId}")
    ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") int questionId) {
        if (questionService.deleteQuestion(questionId)) {
            logger.info("Deleting question");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.error("Could not delete question");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
