package pro.sky.coursework_c2.service;

import pro.sky.coursework_c2.entity.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(Integer amount);
}