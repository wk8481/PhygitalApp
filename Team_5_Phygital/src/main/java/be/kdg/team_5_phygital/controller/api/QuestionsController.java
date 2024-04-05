package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewQuestionDto;
import be.kdg.team_5_phygital.controller.api.dto.QuestionDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateQuestionDto;
import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.repository.QuestionRepository;
import be.kdg.team_5_phygital.service.QuestionService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {
    private final QuestionService questionService;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public QuestionsController(QuestionService questionService, QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.questionService = questionService;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    ResponseEntity<QuestionDto> saveQuestion(@RequestBody @Valid NewQuestionDto questionDto) {
        if (questionRepository.findByText(questionDto.getText()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Question createdQuestion = questionService.saveQuestion(questionDto.getText(), questionDto.getType(), questionDto.getSubThemeId());
        return new ResponseEntity<>(modelMapper.map(createdQuestion, QuestionDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{questionId}")
    ResponseEntity<Void> updateQuestion(@PathVariable int questionId, @RequestBody UpdateQuestionDto updateQuestionDto) {
        if (questionService.updateQuestion(questionId, updateQuestionDto.getText(), updateQuestionDto.getType())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{questionId}")
    ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") int questionId) {
        if (questionService.deleteQuestion(questionId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
