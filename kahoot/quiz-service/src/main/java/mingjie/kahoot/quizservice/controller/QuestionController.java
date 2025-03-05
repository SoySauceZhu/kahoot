package mingjie.kahoot.quizservice.controller;

import mingjie.kahoot.quizservice.entity.Question;
import mingjie.kahoot.quizservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getQuestionsByQuizId(@RequestParam Long quizId) {
        return questionService.getQuestionsByQuizId(quizId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(question);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question newQuestion) {
        Question savedQuestion = questionService.createQuestion(newQuestion);
        return ResponseEntity.ok(savedQuestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        Question dbQuestion = questionService.getQuestionById(id);
        if (dbQuestion == null) {
            return ResponseEntity.notFound().build();
        }
        dbQuestion.setQuestionText(updatedQuestion.getQuestionText());
        dbQuestion.setQuestionType(updatedQuestion.getQuestionType());
        questionService.updateQuestion(dbQuestion);
        return ResponseEntity.ok(dbQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        Question dbQuestion = questionService.getQuestionById(id);
        if (dbQuestion == null) {
            return ResponseEntity.notFound().build();
        }
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}