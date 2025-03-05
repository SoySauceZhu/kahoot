package mingjie.kahoot.quizeservice.controller;

import mingjie.kahoot.quizeservice.entity.Option;
import mingjie.kahoot.quizeservice.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public List<Option> getOptionsByQuestionId(@RequestParam Long questionId) {
        return optionService.getOptionsByQuestionId(questionId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Option> getOptionById(@PathVariable Long id) {
        Option option = optionService.getOptionById(id);
        if (option == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(option);
    }

    @PostMapping
    public ResponseEntity<Option> createOption(@RequestBody Option newOption) {
        Option savedOption = optionService.createOption(newOption);
        return ResponseEntity.ok(savedOption);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Option> updateOption(@PathVariable Long id, @RequestBody Option updatedOption) {
        Option dbOption = optionService.getOptionById(id);
        if (dbOption == null) {
            return ResponseEntity.notFound().build();
        }
        dbOption.setOptionText(updatedOption.getOptionText());
        dbOption.setCorrect(updatedOption.isCorrect());
        optionService.updateOption(dbOption);
        return ResponseEntity.ok(dbOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        Option dbOption = optionService.getOptionById(id);
        if (dbOption == null) {
            return ResponseEntity.notFound().build();
        }
        optionService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}