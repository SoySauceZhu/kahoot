package mingjie.kahoot.gameservice.controller;

import com.github.pagehelper.PageInfo;
import mingjie.kahoot.gameservice.dto.QuestionCreateRequest;
import mingjie.kahoot.gameservice.dto.QuestionUpdateRequest;
import mingjie.kahoot.gameservice.model.Question;
import mingjie.kahoot.gameservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionCreateRequest request, @RequestParam Long gameId) {
        Question question = questionService.createQuestion(request, gameId);
        return ResponseEntity.ok(question);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<Void> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionUpdateRequest request, @RequestParam Long userId) {
        questionService.updateQuestion(questionId, request, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId, @RequestParam Long userId) {
        questionService.deleteQuestion(questionId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long questionId) {
        Question question = questionService.getQuestion(questionId);
        return ResponseEntity.ok(question);
    }

    @GetMapping
    public ResponseEntity<PageInfo<Question>> listQuestions(@RequestParam Long gameId) {
        PageInfo<Question> questions = questionService.getQuestionPages(gameId);
        return ResponseEntity.ok(questions);
    }
}
