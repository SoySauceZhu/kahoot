package mingjie.kahoot.gameservice.controller;

import com.github.pagehelper.PageInfo;
import mingjie.kahoot.gameservice.dto.OptionCreateRequest;
import mingjie.kahoot.gameservice.model.Option;
import mingjie.kahoot.gameservice.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/options")
public class OptionController {
    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping
    public ResponseEntity<Option> createOption(@RequestBody OptionCreateRequest request, @RequestParam Long questionId) {
        Option option = optionService.createOption(request, questionId);
        return ResponseEntity.ok(option);
    }

    @PutMapping("/{optionId}")
    public ResponseEntity<Void> updateOption(@PathVariable Long optionId, @RequestBody OptionCreateRequest request, @RequestParam Long userId) {
        optionService.updateOption(optionId, request, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{optionId}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long optionId, @RequestParam Long userId) {
        optionService.deleteOption(optionId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{optionId}")
    public ResponseEntity<Option> getOption(@PathVariable Long optionId) {
        Option option = optionService.getOption(optionId);
        return ResponseEntity.ok(option);
    }

    @GetMapping()
    public ResponseEntity<PageInfo<Option>> getOptionsByQuestionId(@RequestParam Long questionId) {
        PageInfo<Option> options = optionService.getOptionByQuestionId(questionId);
        return ResponseEntity.ok(options);
    }
}