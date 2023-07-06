package pro.sky.coursework_c2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework_c2.entity.Question;
import pro.sky.coursework_c2.repository.impl.JavaQuestionRepository;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionRepositoryTest {
    @InjectMocks
    private JavaQuestionRepository repository;
    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question("Вопрос.1.", "Ответ.1.");
        repository.add(new Question("Вопрос.2.", "Ответ.2."));
        repository.add(new Question("Вопрос.3.", "Ответ.3."));
    }

    @Test
    void testAdd() {

        Question addedQuestion = repository.add(question);

        assertEquals(question, addedQuestion);
    }

    @Test
    void testRemove() {

        repository.add(question);

        Question removedQuestion = repository.remove(question.getQuestion(), question.getAnswer());

        assertEquals(question, removedQuestion);
    }

    @Test
    void testGetAll() {

        Collection<Question> allQuestions = repository.getAll();

        assertEquals(2, allQuestions.size());
    }
}