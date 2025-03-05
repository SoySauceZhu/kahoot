package mingjie.kahoot.quizservice.dao;

import mingjie.kahoot.quizservice.entity.Quiz;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuizMapper {

    @Select("SELECT id, title, description, created_at, updated_at FROM quizzes WHERE id = #{id}")
    Quiz findById(Long id);

    @Select("SELECT id, title, description, created_at, updated_at FROM quizzes")
    List<Quiz> findAll();

    @Insert("INSERT INTO quizzes (title, description, created_at, updated_at) " +
            "VALUES (#{title}, #{description}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertQuiz(Quiz quiz);

    @Update("UPDATE quizzes SET title = #{title}, description = #{description}, updated_at = NOW() " +
            "WHERE id = #{id}")
    int updateQuiz(Quiz quiz);

    @Delete("DELETE FROM quizzes WHERE id = #{id}")
    int deleteQuiz(Long id);
}