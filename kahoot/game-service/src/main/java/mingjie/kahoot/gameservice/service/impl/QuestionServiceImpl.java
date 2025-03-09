package mingjie.kahoot.gameservice.service.impl;

import com.github.pagehelper.PageInfo;
import mingjie.kahoot.gameservice.dto.QuestionCreateRequest;
import mingjie.kahoot.gameservice.dto.QuestionUpdateRequest;
import mingjie.kahoot.gameservice.mapper.GameMapper;
import mingjie.kahoot.gameservice.mapper.QuestionMapper;
import mingjie.kahoot.gameservice.model.Game;
import mingjie.kahoot.gameservice.model.Question;
import mingjie.kahoot.gameservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.now;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;
    private final GameMapper gameMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper, GameMapper gameMapper) {
        this.questionMapper = questionMapper;
        this.gameMapper = gameMapper;
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
    public void updateQuestion(Long questionId, QuestionUpdateRequest request, Long userId) {
        Question question = questionMapper.findById(questionId);
        Game game = gameMapper.findById(question.getGameId());
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (!Objects.equals(game.getCreatorId(), userId)) {
            throw new IllegalArgumentException("User is not the creator of the game");
        }

        if (request.getContent() != null) {
            question.setContent(request.getContent());
        }
        if (request.getTimeLimit() != null) {
            question.setTimeLimit(request.getTimeLimit());
        }
        if (request.getCorrectAnswer() != null) {
            question.setCorrectAnswer(request.getCorrectAnswer());
        }
        question.setUpdatedAt(now());

        questionMapper.update(question);
    }

    @Override
    public void deleteQuestion(Long questionId, Long userId) {
        Question question = questionMapper.findById(questionId);
        Game game = gameMapper.findById(question.getGameId());
        if (!Objects.equals(game.getCreatorId(), userId)) {
            throw new IllegalArgumentException("User is not the creator of the game");
        }

        questionMapper.delete(questionId);
    }

    @Override
    public Question getQuestionWithAnswer(Long questionId, Long userId) {
        return questionMapper.findById(questionId);
    }

    @Override
    public Question getQuestion(Long questionId) {
        Question question = questionMapper.findById(questionId);
        question.setCorrectAnswer(null);
        return question;
    }

    @Override
    public PageInfo<Question> getQuestionPages(Long gameId) {
        List<Question> questions = questionMapper.findByGameId(gameId);
        for (Question question : questions) {
            question.setCorrectAnswer(null);
        }
        return new PageInfo<>(questions);
    }

}
