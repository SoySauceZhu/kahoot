package mingjie.kahoot.quizservice.controller;

import mingjie.kahoot.quizservice.entity.Quiz;
import mingjie.kahoot.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.getQuizById(id);
        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quiz);
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz newQuiz) {
        Quiz savedQuiz = quizService.createQuiz(newQuiz);
        return ResponseEntity.ok(savedQuiz);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) {
        Quiz dbQuiz = quizService.getQuizById(id);
        if (dbQuiz == null) {
            return ResponseEntity.notFound().build();
        }
        dbQuiz.setTitle(updatedQuiz.getTitle());
        dbQuiz.setDescription(updatedQuiz.getDescription());
        quizService.updateQuiz(dbQuiz);
        return ResponseEntity.ok(dbQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        Quiz dbQuiz = quizService.getQuizById(id);
        if (dbQuiz == null) {
            return ResponseEntity.notFound().build();
        }
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}