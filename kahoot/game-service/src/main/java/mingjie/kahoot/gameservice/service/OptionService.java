package mingjie.kahoot.gameservice.service;

import mingjie.kahoot.gameservice.dto.OptionCreateRequest;
import mingjie.kahoot.gameservice.model.Option;

public interface OptionService {

    void createOption(OptionCreateRequest request, Long questionId);

    Option getOption(Long optionId);

    void updateOption(Long optionId, OptionCreateRequest request);

    void deleteOption(Long optionId);
}
