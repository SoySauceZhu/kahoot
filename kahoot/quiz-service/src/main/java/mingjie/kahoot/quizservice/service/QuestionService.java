package mingjie.kahoot.quizservice.service;

import mingjie.kahoot.quizservice.dao.QuestionMapper;
import mingjie.kahoot.quizservice.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionService(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    public Question getQuestionById(Long id) {
        return questionMapper.findById(id);
    }

    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionMapper.findByQuizId(quizId);
    }

    public Question createQuestion(Question question) {
        questionMapper.insertQuestion(question);
        return question;
    }

    public int updateQuestion(Question question) {
        return questionMapper.updateQuestion(question);
    }

    public int deleteQuestion(Long id) {
        return questionMapper.deleteQuestion(id);
    }
}