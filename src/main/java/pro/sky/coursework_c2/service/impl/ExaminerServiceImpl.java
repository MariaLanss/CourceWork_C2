package pro.sky.coursework_c2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.coursework_c2.entity.Question;
import pro.sky.coursework_c2.exceptions.WrongAmountException;
import pro.sky.coursework_c2.service.ExaminerService;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;

    @Override
    public Collection<Question> getQuestions(final Integer amount) {
        Random random = new Random();
        if (amount == 0) {
            throw new WrongAmountException("Количество запросов не может быть - 0");
        }

        var javaQuestions = javaQuestionService.getAll();
        var mathQuestions = mathQuestionService.getAll();
        if (amount > (javaQuestions.size() + mathQuestions.size())) {
            throw new WrongAmountException("Отсутствует количество запросов");
        }

        return Stream.generate(() -> {
                    if (javaQuestions.size() == 0 && mathQuestions.size() != 0) {
                        return mathQuestionService.getRandomQuestion();
                    } else if (javaQuestions.size() != 0 && mathQuestions.size() == 0) {
                        return javaQuestionService.getRandomQuestion();
                    } else {
                        return random.nextBoolean()
                                ? javaQuestionService.getRandomQuestion()
                                : mathQuestionService.getRandomQuestion();
                    }
                })
                .distinct()
                .limit(amount)
                .collect(Collectors.toSet());
    }
}
