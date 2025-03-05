package mingjie.kahoot.quizeservice.dao;


import mingjie.kahoot.quizeservice.entity.Option;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OptionMapper {

    @Select("SELECT id, question_id, option_text, is_correct, created_at, updated_at " +
            "FROM options WHERE id = #{id}")
    Option findById(Long id);

    @Select("SELECT id, question_id, option_text, is_correct, created_at, updated_at " +
            "FROM options WHERE question_id = #{questionId}")
    List<Option> findByQuestionId(Long questionId);

    @Insert("INSERT INTO options (question_id, option_text, is_correct, created_at, updated_at) " +
            "VALUES (#{questionId}, #{optionText}, #{isCorrect}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertOption(Option option);

    @Update("UPDATE options SET option_text = #{optionText}, is_correct = #{isCorrect}, updated_at = NOW() " +
            "WHERE id = #{id}")
    int updateOption(Option option);

    @Delete("DELETE FROM options WHERE id = #{id}")
    int deleteOption(Long id);

    @Delete("DELETE FROM options WHERE question_id = #{questionId}")
    int deleteByQuestionId(Long questionId);
}