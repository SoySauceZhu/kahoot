package mingjie.kahoot.gameservice.mapper;

import mingjie.kahoot.gameservice.model.Game;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GameMapper {
    @Select("SELECT * FROM games WHERE id = #{id}")
    Game findById(Long id);

    @Select("SELECT * FROM games WHERE game_code = #{gameCode}")
    Game findByCode(String gameCode);

    @Select("SELECT * FROM games")
    List<Game> findAll();

    @Insert("INSERT INTO games (title, description, game_code, creator_id, status, is_deleted, created_at, updated_at) " +
            "VALUES (#{title}, #{description}, #{gameCode}, #{creatorId}, #{status}, #{isDeleted}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Game game);

    @Update("UPDATE games SET title = #{title}, description = #{description}, game_code = #{gameCode}, creator_id = #{creatorId}, " +
            "status = #{status}, is_deleted = #{isDeleted}, created_at = #{createdAt}, updated_at = #{updatedAt} WHERE id = #{id}")
    void update(Game game);

    @Delete("DELETE FROM games WHERE id = #{id}")
    void delete(Long id);

    @Select("SELECT * FROM games WHERE creator_id = #{userId} AND status = #{status}")
    List<Game> findAllByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);
}