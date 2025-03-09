package mingjie.kahoot.gameservice.service;

import mingjie.kahoot.gameservice.dto.QuestionCreateRequest;
import mingjie.kahoot.gameservice.dto.QuestionUpdateRequest;
import mingjie.kahoot.gameservice.model.Question;

public interface QuestionService {

    Question createQuestion(QuestionCreateRequest request, Long gameId);

    Question getQuestion(Long questionId);

    void updateQuestion(Long questionId, QuestionUpdateRequest request);

    void deleteQuestion(Long questionId);
}
