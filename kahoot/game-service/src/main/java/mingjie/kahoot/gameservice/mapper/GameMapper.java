package mingjie.kahoot.gameservice.mapper;

import mingjie.kahoot.gameservice.model.Game;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GameMapper {

    @Select("SELECT * FROM games WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "description", column = "description"),
        @Result(property = "gameCode", column = "game_code"),
        @Result(property = "creatorId", column = "creator_id"),
        @Result(property = "status", column = "status"),
        @Result(property = "isDeleted", column = "is_deleted"),
        @Result(property = "createdAt", column = "created_at"),
        @Result(property = "updatedAt", column = "updated_at")
    })
    Game findById(Long id);

    @Select("SELECT * FROM games WHERE game_code = #{gameCode}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "description", column = "description"),
        @Result(property = "gameCode", column = "game_code"),
        @Result(property = "creatorId", column = "creator_id"),
        @Result(property = "status", column = "status"),
        @Result(property = "isDeleted", column = "is_deleted"),
        @Result(property = "createdAt", column = "created_at"),
        @Result(property = "updatedAt", column = "updated_at")
    })
    Game findByCode(String gameCode);

    @Select("SELECT * FROM games WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "description", column = "description"),
        @Result(property = "gameCode", column = "game_code"),
        @Result(property = "creatorId", column = "creator_id"),
        @Result(property = "status", column = "status"),
        @Result(property = "isDeleted", column = "is_deleted"),
        @Result(property = "createdAt", column = "created_at"),
        @Result(property = "updatedAt", column = "updated_at")
    })
    List<Game> findAllById(@Param("id") Long userId);

    @Insert("INSERT INTO games (title, description, game_code, creator_id, status, is_deleted, created_at, updated_at) " +
            "VALUES (#{title}, #{description}, #{gameCode}, #{creatorId}, #{status}, #{isDeleted}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Game game);

    @Update("UPDATE games SET title = #{title}, description = #{description}, game_code = #{gameCode}, creator_id = #{creatorId}, " +
            "status = #{status}, is_deleted = #{isDeleted}, created_at = #{createdAt}, updated_at = #{updatedAt} WHERE id = #{id}")
    void update(Game game);

    @Delete("DELETE FROM games WHERE id = #{id}")
    void delete(Long id);

    @Select("SELECT * FROM games WHERE creator_id = #{userId} AND status = #{status} LIMIT #{size} OFFSET #{offset}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "description", column = "description"),
        @Result(property = "gameCode", column = "game_code"),
        @Result(property = "creatorId", column = "creator_id"),
        @Result(property = "status", column = "status"),
        @Result(property = "isDeleted", column = "is_deleted"),
        @Result(property = "createdAt", column = "created_at"),
        @Result(property = "updatedAt", column = "updated_at")
    })
    List<Game> findAllByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status, @Param("offset") int offset, @Param("size") int size);
}