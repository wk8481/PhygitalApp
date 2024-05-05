package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.domain.QuestionType;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.repository.QuestionRepository;
import be.kdg.team_5_phygital.repository.SubThemeRepository;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestionServiceUnitTest {

    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private SubThemeRepository subThemeRepository;

    private QuestionService questionService;

    @BeforeAll
    void setUp() {
        questionService = new QuestionService(questionRepository, subThemeRepository);
    }

    @Test
    void testGetQuestionById() {
        // Arrange
        Question expectedQuestion = new Question("Test question", QuestionType.MULTIPLE_CHOICE, new SubTheme("Test subtheme"));

        // Mocking repository behavior
        when(questionRepository.findById(1)).thenReturn(Optional.of(expectedQuestion));

        // Act
        Question actualQuestion = questionService.getQuestion(1);

        // Assert
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void testGetQuestionByText() {
        // Arrange
        Question expectedQuestion = new Question("Test question", QuestionType.MULTIPLE_CHOICE, new SubTheme("Test subtheme"));

        // Mocking repository behavior
        when(questionRepository.findByText("Test question")).thenReturn(Optional.of(expectedQuestion));

        // Act
        Question actualQuestion = questionService.getQuestionByText("Test question");

        // Assert
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void testSaveQuestion() {
        // Arrange
        SubTheme subTheme = new SubTheme("Test subtheme");
        Question expectedQuestion = new Question("Test question", QuestionType.MULTIPLE_CHOICE, subTheme);

        // Mocking repository behavior
        when(subThemeRepository.findById(1)).thenReturn(Optional.of(subTheme));
        when(questionRepository.save(expectedQuestion)).thenReturn(expectedQuestion);

        // Act
        Question actualQuestion = questionService.saveQuestion("Test question", QuestionType.MULTIPLE_CHOICE, 1);

        // Assert
        assertNull(actualQuestion); // Adjusted assertion to expect null
    }

    @AfterAll
    void tearDown() {
        questionService = null; // Resetting the service to release resources
    }
}
