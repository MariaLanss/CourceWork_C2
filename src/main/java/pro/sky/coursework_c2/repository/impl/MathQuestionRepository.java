package pro.sky.coursework_c2.repository.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import pro.sky.coursework_c2.entity.Question;
import pro.sky.coursework_c2.repository.QuestionRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    private final Set<Question> mathQuestions = new HashSet<>();

    @Override
    public Question add(Question question) {
        if (mathQuestions.add(question)) {
            return question;
        }
        return null;
    }

    @Override
    public Question remove(String question, String answer) {
        var mathQuestion = new Question(question, answer);
        if (mathQuestions.remove(mathQuestion)) {
            return mathQuestion;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(mathQuestions);
    }

    @PostConstruct
    private void init() {
        mathQuestions.add(new Question("Матем.1.", "Матем.1."));
        mathQuestions.add(new Question("Матем.2.", "Матем.2."));
        mathQuestions.add(new Question("Матем.3.", "Матем.3."));
        mathQuestions.add(new Question("Матем.4.", "Матем.4."));
        mathQuestions.add(new Question("Матем.5.", "Матем.5."));
    }
}