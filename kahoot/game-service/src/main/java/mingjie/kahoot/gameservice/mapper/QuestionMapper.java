package mingjie.kahoot.gameservice.mapper;

    import mingjie.kahoot.gameservice.model.Question;
    import org.apache.ibatis.annotations.*;

    import java.util.List;

    @Mapper
    public interface QuestionMapper {

        @Select("SELECT * FROM questions WHERE id = #{id}")
        @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gameId", column = "game_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "type", column = "type"),
            @Result(property = "timeLimit", column = "time_limit"),
            @Result(property = "correctAnswer", column = "correct_answer", typeHandler = mingjie.kahoot.gameservice.typehandler.LongArrayTypeHandler.class),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
        })
        Question findById(Long id);

        @Select("SELECT * FROM questions WHERE game_id = #{gameId}")
        @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gameId", column = "game_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "type", column = "type"),
            @Result(property = "timeLimit", column = "time_limit"),
            @Result(property = "correctAnswer", column = "correct_answer", typeHandler = mingjie.kahoot.gameservice.typehandler.LongArrayTypeHandler.class),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
        })
        List<Question> findByGameId(Long gameId);

        @Select("SELECT * FROM questions")
        @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gameId", column = "game_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "type", column = "type"),
            @Result(property = "timeLimit", column = "time_limit"),
            @Result(property = "correctAnswer", column = "correct_answer", typeHandler = mingjie.kahoot.gameservice.typehandler.LongArrayTypeHandler.class),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
        })
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