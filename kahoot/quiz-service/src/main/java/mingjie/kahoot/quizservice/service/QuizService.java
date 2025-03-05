package mingjie.kahoot.quizservice.service;

import mingjie.kahoot.quizservice.dao.QuestionMapper;
import mingjie.kahoot.quizservice.dao.QuizMapper;
import mingjie.kahoot.quizservice.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuizService {

    private final QuizMapper quizMapper;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuizService(QuizMapper quizMapper, QuestionMapper questionMapper) {
        this.quizMapper = quizMapper;
        this.questionMapper = questionMapper;
    }

    public Quiz getQuizById(Long id) {
        return quizMapper.findById(id);
    }

    public List<Quiz> getAllQuizzes() {
        return quizMapper.findAll();
    }

    public Quiz createQuiz(Quiz quiz) {
        quizMapper.insertQuiz(quiz);
        return quiz;
    }

    public int updateQuiz(Quiz quiz) {
        return quizMapper.updateQuiz(quiz);
    }

    public int deleteQuiz(Long id) {
        questionMapper.deleteByQuizId(id);
        return quizMapper.deleteQuiz(id);
    }
}