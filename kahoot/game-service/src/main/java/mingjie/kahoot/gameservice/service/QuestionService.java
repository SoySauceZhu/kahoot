package mingjie.kahoot.gameservice.service;

import com.github.pagehelper.PageInfo;
import mingjie.kahoot.gameservice.dto.QuestionCreateRequest;
import mingjie.kahoot.gameservice.dto.QuestionUpdateRequest;
import mingjie.kahoot.gameservice.model.Question;

public interface QuestionService {

    // For Creator
    Question createQuestion(QuestionCreateRequest request, Long gameId);

    void updateQuestion(Long questionId, QuestionUpdateRequest request, Long userId);

    void deleteQuestion(Long questionId, Long userId);


    Question getQuestionWithAnswer(Long questionId, Long userId);

    // For User
    Question getQuestion(Long questionId);

    PageInfo<Question> getQuestionPages(Long gameId);
}
