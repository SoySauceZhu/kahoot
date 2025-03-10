package mingjie.kahoot.gameservice.service;

import com.github.pagehelper.PageInfo;
import mingjie.kahoot.gameservice.dto.OptionCreateRequest;
import mingjie.kahoot.gameservice.model.Option;

public interface OptionService {

    Option createOption(OptionCreateRequest request, Long questionId);

    Option getOption(Long optionId);

    PageInfo<Option> getOptionByQuestionId(Long questionId);

    void updateOption(Long optionId, OptionCreateRequest request, Long userId);

    void deleteOption(Long optionId, Long userId);
}
