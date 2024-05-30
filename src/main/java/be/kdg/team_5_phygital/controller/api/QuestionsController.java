package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewAnswerDto;
import be.kdg.team_5_phygital.controller.api.dto.NewQuestionDto;
import be.kdg.team_5_phygital.controller.api.dto.QuestionDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateQuestionDto;
import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.service.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final QuestionService questionService;
    private final SubThemeService subThemeService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final AnswerService answerService;
    private final PossibleAnswerService possibleAnswerService;
    private final ProjectService projectService;
    private final SharingPlatformService sharingPlatformService;
    private final SessionService sessionService;
    private final NotesService notesService;
    private final FlowService flowService;

    public QuestionsController(QuestionService questionService, SubThemeService subThemeService, ModelMapper modelMapper, UserService userService, AnswerService answerService, PossibleAnswerService possibleAnswerService, ProjectService projectService, SharingPlatformService sharingPlatformService, SessionService sessionService, NotesService notesService, FlowService flowService) {
        this.questionService = questionService;
        this.subThemeService = subThemeService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.answerService = answerService;
        this.possibleAnswerService = possibleAnswerService;
        this.projectService = projectService;
        this.sharingPlatformService = sharingPlatformService;
        this.sessionService = sessionService;
        this.notesService = notesService;
        this.flowService = flowService;
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
        SubTheme subTheme = subThemeService.getSubTheme(subThemeId);
        List<Question> allQuestions = questionService.getQuestionsBySubTheme(subTheme);
        List<QuestionDto> questionDtos = allQuestions.stream().map(question -> modelMapper.map(question, QuestionDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(questionDtos);
    }

    @PostMapping
    ResponseEntity<QuestionDto> saveQuestion(@RequestBody @Valid NewQuestionDto questionDto) {
        if (questionService.getQuestionByText(questionDto.getText()) != null) {
            logger.error("Could not create new question");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Creating new question: {} with answers: {} size: {}", questionDto.getText(), questionDto.getAnswers(), questionDto.getAnswers().size());
        Question createdQuestion = questionService.saveQuestion(questionDto.getText(), questionDto.getType(), questionDto.getSubThemeId());
        if (questionDto.getAnswers().size() != 1) {
            for (String answer : questionDto.getAnswers()) {
                possibleAnswerService.savePossibleAnswers(answer, createdQuestion);
            }
        }
        return new ResponseEntity<>(modelMapper.map(createdQuestion, QuestionDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitAnswer(@RequestBody NewAnswerDto newAnswerDto) {
        Answers answer = modelMapper.map(newAnswerDto, Answers.class);
        if (answer != null) {
            User user = userService.getUserByMail(newAnswerDto.getUserMail());
            SubTheme subTheme = subThemeService.getSubTheme(newAnswerDto.getSubThemeId());
            Map<String, String> questionAnswerMap = new HashMap<>();

            // Split question and answer strings by "|" delimiter
            String[] questions = newAnswerDto.getQuestion().split("\\|");
            String[] answers = newAnswerDto.getAnswer().split("\\|");

            // Ensure the lengths of questions and answers are the same
            if (questions.length == answers.length) {
                for (int i = 0; i < questions.length; i++) {
                    // Trim to remove leading/trailing spaces
                    questionAnswerMap.put(questions[i].trim(), answers[i].trim());
                }
            } else {
                // Handle the case where the number of questions and answers doesn't match
                throw new IllegalArgumentException("Number of questions does not match number of answers");
            }
            List<Question> questionList = new ArrayList<>();
            List<Answers> answerList = new ArrayList<>();
            for (Map.Entry<String, String> entry : questionAnswerMap.entrySet()) {
                String q = entry.getKey();
                String a = entry.getValue();
                Question question = questionService.getQuestion(Integer.parseInt(q));
                if (a.isBlank()){
                    a = null;
                }
                questionList.add(question);
                Answers answer1 = answerService.saveAnswer(a);
                answerList.add(answer1);
            }

            if (sessionService.getSessionOfUser(user, subTheme).isEmpty()){
                Notes note = notesService.createNote(newAnswerDto.getNote());
                sessionService.createSession(new Session(LocalDateTime.now(), questionList, answerList, user, note, subTheme));
            } else {
                Session session = sessionService.getSessionOfUser(user, subTheme).orElse(null);
                notesService.updateNote(session.getNote(), newAnswerDto.getNote());
                sessionService.addAnswerToSession(session, answerList.get(0));
                sessionService.addQuestionToSession(session, questionList.get(0));
                sessionService.updateTime(session);
            }
            projectService.updateTimeAndParticipants(subTheme.getFlow().getProject(), newAnswerDto.getDurationSpend());
            sharingPlatformService.updateTimeAndParticipants(subTheme.getFlow().getProject().getSharingPlatform(), newAnswerDto.getDurationSpend());
            flowService.updateTimeAndParticipants(subTheme.getFlow(), newAnswerDto.getDurationSpend());

//            logger.info("\n Answers submitted: {} \n to questions: {} \n for sessionid: {} \n with note: {}", session.getAnswers().toString(), session.getQuestions().toString(), session.getSessionId(), session.getNote().getNote());
            return ResponseEntity.status(HttpStatus.CREATED).body("Answer submitted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to submit answer.");
        }
    }

    @PatchMapping("{questionId}")
    ResponseEntity<Void> updateQuestion(@PathVariable int questionId, @RequestBody UpdateQuestionDto updateQuestionDto) {
        if (questionService.updateQuestion(questionId, updateQuestionDto.getText(), updateQuestionDto.getType())) {
            logger.info("Updating question to: {}", updateQuestionDto.getText());
            Question question = questionService.getQuestion(questionId);
            List<Question> questions = new ArrayList<>();
            questions.add(question);
            possibleAnswerService.getPossibleAnswersByQuestionId(questions).forEach(answer -> possibleAnswerService.deletePossibleAnswers(answer.getId()));
            for (String answer : updateQuestionDto.getAnswers()) {
                possibleAnswerService.savePossibleAnswers(answer, questionService.getQuestion(questionId));
            }
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
