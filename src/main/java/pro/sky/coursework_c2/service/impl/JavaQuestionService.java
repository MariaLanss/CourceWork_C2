package pro.sky.coursework_c2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.coursework_c2.entity.Question;
import pro.sky.coursework_c2.exceptions.QuestionAlreadyExistException;
import pro.sky.coursework_c2.exceptions.QuestionNotFoundException;
import pro.sky.coursework_c2.repository.QuestionRepository;
import pro.sky.coursework_c2.service.QuestionService;

import java.util.Collection;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository javaQuestionRepository;

    @Override
    public Question add(final String question, final String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(final Question question) {
        Question addQuestion = javaQuestionRepository.add(question);
        if (addQuestion == null) {
            throw new QuestionAlreadyExistException("Данный вопрос уже существует");
        }
        return addQuestion;
    }

    @Override
    public Question remove(final String question, final String answer) {
        Question removeQuestion = javaQuestionRepository.remove(question, answer);
        if (removeQuestion == null) {
            throw new QuestionNotFoundException("Отсутствует данный вопрос");
        }
        return removeQuestion;
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        int size = javaQuestionRepository.getAll().size();
        if (size > 0) {
            int item = new Random().nextInt(size);
            return javaQuestionRepository.getAll().stream().skip(item).findFirst().orElse(null);
        }
        return null;
    }
}