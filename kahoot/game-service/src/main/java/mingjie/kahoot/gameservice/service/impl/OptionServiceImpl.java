package mingjie.kahoot.gameservice.service.impl;

import com.github.pagehelper.PageInfo;
import mingjie.kahoot.gameservice.dto.OptionCreateRequest;
import mingjie.kahoot.gameservice.mapper.GameMapper;
import mingjie.kahoot.gameservice.mapper.OptionMapper;
import mingjie.kahoot.gameservice.mapper.QuestionMapper;
import mingjie.kahoot.gameservice.model.Game;
import mingjie.kahoot.gameservice.model.Option;
import mingjie.kahoot.gameservice.model.Question;
import mingjie.kahoot.gameservice.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionMapper optionMapper;
    private final QuestionMapper questionMapper;
    private final GameMapper gameMapper;

    @Autowired
    public OptionServiceImpl(OptionMapper optionMapper, QuestionMapper questionMapper, GameMapper gameMapper) {
        this.optionMapper = optionMapper;
        this.questionMapper = questionMapper;
        this.gameMapper = gameMapper;
    }


    @Override
    public Option createOption(OptionCreateRequest request, Long questionId) {
        Option option = new Option();
        option.setQuestionId(questionId);
        option.setContent(request.getContent());
        option.setOrder(request.getOrder());

        optionMapper.insert(option);

        return option;
    }

    @Override
    public Option getOption(Long optionId) {
        return optionMapper.findById(optionId);
    }

    @Override
    public PageInfo<Option> getOptionByQuestionId(Long questionId) {
        List<Option> optionList = optionMapper.findByQuestionId(questionId);
        return new PageInfo<>(optionList);
    }

    @Override
    public void updateOption(Long optionId, OptionCreateRequest request, Long userId) {


        Option option = optionMapper.findById(optionId);

        if (option == null) {
            throw new IllegalArgumentException("Option not found");
        }

        Question question = questionMapper.findById(option.getQuestionId());
        Game game = gameMapper.findById(question.getGameId());
        if (!userId.equals(game.getCreatorId())) {
            throw new IllegalArgumentException("User is not authorized to update this option");
        }

        if (request.getContent() != null) {
            option.setContent(request.getContent());
        }
        option.setOrder(request.getOrder());
        optionMapper.update(option);
    }

    @Override
    public void deleteOption(Long optionId, Long userId) {
        Option option = optionMapper.findById(optionId);

        if (option == null) {
            throw new IllegalArgumentException("Option not found");
        }

        Question question = questionMapper.findById(option.getQuestionId());
        Game game = gameMapper.findById(question.getGameId());
        if (!userId.equals(game.getCreatorId())) {
            throw new IllegalArgumentException("User is not authorized to update this option");
        }

        optionMapper.delete(optionId);
    }
}
