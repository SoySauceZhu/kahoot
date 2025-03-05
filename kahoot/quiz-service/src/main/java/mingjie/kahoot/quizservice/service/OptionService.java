package mingjie.kahoot.quizservice.service;

import mingjie.kahoot.quizservice.dao.OptionMapper;
import mingjie.kahoot.quizservice.entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

    private final OptionMapper optionMapper;

    @Autowired
    public OptionService(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }

    public Option getOptionById(Long id) {
        return optionMapper.findById(id);
    }

    public List<Option> getOptionsByQuestionId(Long questionId) {
        return optionMapper.findByQuestionId(questionId);
    }

    public Option createOption(Option option) {
        optionMapper.insertOption(option);
        return option;
    }

    public int updateOption(Option option) {
        return optionMapper.updateOption(option);
    }

    public int deleteOption(Long id) {
        return optionMapper.deleteOption(id);
    }
}