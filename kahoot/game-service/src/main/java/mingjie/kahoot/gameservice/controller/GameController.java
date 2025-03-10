package mingjie.kahoot.gameservice.controller;

import com.github.pagehelper.PageInfo;
import mingjie.kahoot.gameservice.dto.GameCreateRequest;
import mingjie.kahoot.gameservice.dto.GameUpdateRequest;
import mingjie.kahoot.gameservice.model.Game;
import mingjie.kahoot.gameservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody GameCreateRequest request, @RequestParam Long creatorId) {
        Game gameDTO = gameService.createGame(request, creatorId);

        return ResponseEntity.ok(gameDTO);
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<Void> updateGame(@PathVariable Long gameId, @RequestBody GameUpdateRequest request, @RequestParam Long userId) {
        gameService.updateGame(gameId, request, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId, @RequestParam Long userId) {
        gameService.deleteGame(gameId, userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gameId}/publish")
    public ResponseEntity<Void> publishGame(@PathVariable Long gameId, @RequestParam Long userId) {
        gameService.publishGame(gameId, userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gameId}/end")
    public ResponseEntity<Void> endGame(@PathVariable Long gameId, @RequestParam Long userId) {
        gameService.endGame(gameId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageInfo<Game>> listGames(@RequestParam Long userId, @RequestParam String status,
                                                    @RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "6") int size) {
        PageInfo<Game> games = gameService.getGamePages(userId, status, page, size);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<List<Game>> getGames(@PathVariable Long gameId) {
        List<Game> games = gameService.listAllGames(gameId);
        return ResponseEntity.ok(games);
    }

}
