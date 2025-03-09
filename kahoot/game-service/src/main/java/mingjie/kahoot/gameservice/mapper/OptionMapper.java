package mingjie.kahoot.gameservice.mapper;

import mingjie.kahoot.gameservice.model.Option;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OptionMapper {

    @Select("SELECT * FROM question_options WHERE id = #{id}")
    Option findById(Long id);

    @Select("SELECT * FROM question_options WHERE question_id = #{questionId}")
    List<Option> findByQuestionId(Long questionId);

    @Select("SELECT * FROM question_options")
    List<Option> findAll();

    @Insert("INSERT INTO question_options (question_id, content, `order`) " +
            "VALUES (#{questionId}, #{content}, #{order})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Option option);

    @Update("UPDATE question_options SET question_id = #{questionId}, content = #{content}, `order` = #{order} WHERE id = #{id}")
    void update(Option option);

    @Delete("DELETE FROM question_options WHERE id = #{id}")
    void delete(Long id);

}