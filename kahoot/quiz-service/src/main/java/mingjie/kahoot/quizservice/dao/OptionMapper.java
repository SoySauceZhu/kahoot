package mingjie.kahoot.quizservice.dao;


import mingjie.kahoot.quizservice.entity.Option;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OptionMapper {

    Option findById(Long id);

    List<Option> findByQuestionId(Long questionId);

    int insertOption(Option option);

    int updateOption(Option option);

    int deleteOption(Long id);

    int deleteByQuestionId(Long questionId);
}