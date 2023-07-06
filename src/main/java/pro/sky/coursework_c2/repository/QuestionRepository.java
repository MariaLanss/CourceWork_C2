package pro.sky.coursework_c2.repository;

import pro.sky.coursework_c2.entity.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);

    Question remove(String question, String answer);

    Collection<Question> getAll();
}