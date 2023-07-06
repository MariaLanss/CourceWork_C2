package pro.sky.coursework_c2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework_c2.entity.Question;
import pro.sky.coursework_c2.repository.impl.MathQuestionRepository;
import pro.sky.coursework_c2.service.impl.MathQuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {
    @Mock
    private MathQuestionRepository mathQuestionRepository;
    @InjectMocks
    private MathQuestionService mathQuestionService;

    private String question;
    private String answer;
    private List<Question> mockQuestions;

    @BeforeEach
    public void setup() {
        question = "Сколько будет 6 + 6?";
        answer = "12";
        mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question(question, answer));
        mockQuestions.add(new Question("Сколько будет 7 - 4?", "3"));
        mockQuestions.add(new Question("Сколько будет 8 * 6?", "48"));
    }

    @Test
    public void testAddMathQuestionWithQuestionAndAnswer() {

        when(mathQuestionRepository.add(any(Question.class))).thenReturn(mockQuestions.get(0));

        Question result = mathQuestionService.add(question, answer);

        verify(mathQuestionRepository, times(1)).add(mockQuestions.get(0));
        assertEquals(mockQuestions.get(0), result);
    }

    @Test
    public void testAddMathQuestionWithQuestion() {

        when(mathQuestionRepository.add(any(Question.class))).thenReturn(mockQuestions.get(0));

        Question result = mathQuestionService.add(mockQuestions.get(0));

        verify(mathQuestionRepository, times(1)).add(mockQuestions.get(0));
        assertEquals(mockQuestions.get(0), result);
    }

    @Test
    public void testRemoveMathQuestion() {

        when(mathQuestionRepository.remove(question, answer)).thenReturn(mockQuestions.get(0));

        Question result = mathQuestionService.remove(question, answer);

        verify(mathQuestionRepository, times(1)).remove(question, answer);
        assertEquals(mockQuestions.get(0), result);
    }

    @Test
    public void testGetAllMathQuestions() {

        when(mathQuestionRepository.getAll()).thenReturn(mockQuestions);

        Collection<Question> result = mathQuestionService.getAll();

        verify(mathQuestionRepository, times(1)).getAll();
        assertEquals(mockQuestions, result);
    }

    @Test
    public void testGetRandomQuestion() {

        when(mathQuestionRepository.getAll()).thenReturn(mockQuestions);

        Question result = mathQuestionService.getRandomQuestion();

        Optional<Question> expectedQuestion = mockQuestions.stream().filter(q -> q.equals(result)).findFirst();
        assertEquals(expectedQuestion.orElse(null), result);
        verify(mathQuestionRepository, times(2)).getAll();
    }
}