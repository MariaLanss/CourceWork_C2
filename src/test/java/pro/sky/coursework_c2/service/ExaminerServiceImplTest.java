package pro.sky.coursework_c2.service;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework_c2.entity.Question;
import pro.sky.coursework_c2.exceptions.WrongAmountException;
import pro.sky.coursework_c2.service.impl.ExaminerServiceImpl;
import pro.sky.coursework_c2.service.impl.JavaQuestionService;
import pro.sky.coursework_c2.service.impl.MathQuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;
    private List<Question> javaQuestions;
    private List<Question> mathQuestions;

    @BeforeEach
    public void setup() {
        javaQuestions = new ArrayList<>();
        mathQuestions = new ArrayList<>();
        javaQuestions.add(new Question("Джава Вопрос .1.", "Ответ .1."));
        javaQuestions.add(new Question("Джава Вопрос .2.", "Ответ .2."));
        mathQuestions.add(new Question("Матем Вопрос .1.", "Ответ .1."));
        mathQuestions.add(new Question("Матем Вопрос .2.", "Ответ .2."));
    }

    @Test
    public void testGetQuestions() {

        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);
        when(javaQuestionService.getRandomQuestion()).thenReturn(javaQuestions.get(0)).thenReturn(javaQuestions.get(1));
        when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestions.get(0)).thenReturn(mathQuestions.get(1));

        Collection<Question> result = examinerService.getQuestions(3);

        assertEquals(3, result.size());
        assertTrue(result.contains(javaQuestions.get(0)) ||
                result.contains(javaQuestions.get(1)));
        assertTrue(result.contains(mathQuestions.get(0)) ||
                result.contains(mathQuestions.get(1)));
    }

    @Test
    void testGetQuestionsWithZeroAmount() {
        assertThrows(WrongAmountException.class, () ->
                examinerService.getQuestions(0)
        );
    }

    @Test
    void testGetQuestionsWithInvalidAmount() {

        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

        assertThrows(WrongAmountException.class, () ->
                examinerService.getQuestions(5)
        );
    }
}
