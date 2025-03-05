package mingjie.kahoot.quizeservice.dao;

import mingjie.kahoot.quizeservice.entity.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Select("SELECT id, quiz_id, question_text, question_type, created_at, updated_at " +
            "FROM questions WHERE id = #{id}")
    Question findById(Long id);

    @Select("SELECT id, quiz_id, question_text, question_type, created_at, updated_at " +
            "FROM questions WHERE quiz_id = #{quizId}")
    List<Question> findByQuizId(Long quizId);

    @Insert("INSERT INTO questions (quiz_id, question_text, question_type, created_at, updated_at) " +
            "VALUES (#{quizId}, #{questionText}, #{questionType}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertQuestion(Question question);

    @Update("UPDATE questions SET question_text = #{questionText}, question_type = #{questionType}, updated_at = NOW() " +
            "WHERE id = #{id}")
    int updateQuestion(Question question);

    @Delete("DELETE FROM questions WHERE id = #{id}")
    int deleteQuestion(Long id);

    @Delete("DELETE FROM questions WHERE quiz_id = #{quizId}")
    int deleteByQuizId(Long quizId);
}