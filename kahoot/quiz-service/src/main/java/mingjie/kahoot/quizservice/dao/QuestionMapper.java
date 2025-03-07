package mingjie.kahoot.quizservice.dao;

import mingjie.kahoot.quizservice.entity.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    Question findById(Long id);

    List<Question> findByQuizId(Long quizId);

    int insertQuestion(Question question);

    int updateQuestion(Question question);

    int deleteQuestion(Long id);

    int deleteByQuizId(Long quizId);
}