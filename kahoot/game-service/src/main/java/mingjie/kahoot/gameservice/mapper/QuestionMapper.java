package mingjie.kahoot.gameservice.mapper;

import mingjie.kahoot.gameservice.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Select("SELECT * FROM questions WHERE id = #{id}")
    Question findById(Long id);

    @Select("SELECT * FROM questions WHERE game_id = #{gameId}")
    List<Question> findByGameId(Long gameId);

    @Select("SELECT * FROM questions")
    List<Question> findAll();

    @Insert("INSERT INTO questions (game_id, content, time_limit, correct_answer, created_at, updated_at) " +
            "VALUES (#{gameId}, #{content}, #{timeLimit}, #{correctAnswer, typeHandler=mingjie.kahoot.gameservice.typehandler.LongArrayTypeHandler}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Question question);

    @Update("UPDATE questions SET game_id = #{gameId}, content = #{content}, type = #{type}, time_limit = #{timeLimit}, " +
            "correct_answer = #{correctAnswer, typeHandler=mingjie.kahoot.gameservice.typehandler.LongArrayTypeHandler}, created_at = #{createdAt}, updated_at = #{updatedAt} WHERE id = #{id}")
    void update(Question question);

    @Delete("DELETE FROM questions WHERE id = #{id}")
    void delete(Long id);
}