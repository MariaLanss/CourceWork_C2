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
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> javaQuestions = new HashSet<>();

    @Override
    public Question add(Question question) {
        if (javaQuestions.add(question)) {
            return question;
        }
        return null;
    }

    @Override
    public Question remove(String question, String answer) {
        var javaQuestion = new Question(question, answer);
        if (javaQuestions.remove(javaQuestion)) {
            return javaQuestion;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(javaQuestions);
    }

    @PostConstruct
    private void init() {
        javaQuestions.add(new Question("Джава.1.", "Джава.1."));
        javaQuestions.add(new Question("Джава.2.", "Джава.2."));
        javaQuestions.add(new Question("Джава.3.", "Джава.3."));
        javaQuestions.add(new Question("Джава.4.", "Джава.4."));
        javaQuestions.add(new Question("Джава.5.", "Джава.5."));
    }
}
