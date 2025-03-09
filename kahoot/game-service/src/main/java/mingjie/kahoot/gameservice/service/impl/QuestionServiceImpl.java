package mingjie.kahoot.gameservice.service.impl;

import mingjie.kahoot.gameservice.dto.QuestionCreateRequest;
import mingjie.kahoot.gameservice.dto.QuestionUpdateRequest;
import mingjie.kahoot.gameservice.mapper.QuestionMapper;
import mingjie.kahoot.gameservice.model.Question;
import mingjie.kahoot.gameservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public Question createQuestion(QuestionCreateRequest questionCreateRequest, Long gameId) {

        Question question = new Question();
        question.setGameId(gameId);
        question.setContent(questionCreateRequest.getContent());
        question.setTimeLimit(questionCreateRequest.getTimeLimit());
        question.setCorrectAnswer(questionCreateRequest.getCorrectAnswer());
        question.setCreatedAt(now());
        question.setUpdatedAt(now());
        questionMapper.insert(question);

        return question;
    }

    @Override
    public Question getQuestion(Long questionId) {
        return questionMapper.findById(questionId);
    }

    @Override
    public void updateQuestion(Long questionId, QuestionUpdateRequest request) {
    }

    @Override
    public void deleteQuestion(Long questionId) {

    }
}
