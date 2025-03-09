package mingjie.kahoot.gameservice.service.impl;

import mingjie.kahoot.gameservice.dto.OptionCreateRequest;
import mingjie.kahoot.gameservice.mapper.OptionMapper;
import mingjie.kahoot.gameservice.model.Option;
import mingjie.kahoot.gameservice.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionMapper optionMapper;

    @Autowired
    public OptionServiceImpl(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }


    @Override
    public void createOption(OptionCreateRequest request, Long questionId) {
        Option option = new Option();
        option.setQuestionId(questionId);
        option.setContent(request.getContent());
        option.setOrder(request.getOrder());

        optionMapper.insert(option);

    }

    @Override
    public Option getOption(Long optionId) {
        return optionMapper.findById(optionId);
    }

    @Override
    public void updateOption(Long optionId, OptionCreateRequest request) {
        Option option = optionMapper.findById(optionId);
        if (option != null) {
            if (request.getContent() != null) {
                option.setContent(request.getContent());
            }
            option.setOrder(request.getOrder());
            optionMapper.update(option);
        }
    }

    @Override
    public void deleteOption(Long optionId) {
        optionMapper.delete(optionId);
    }
}
