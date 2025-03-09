package mingjie.kahoot.gameservice.controller;

import mingjie.kahoot.gameservice.dto.GameCreateRequest;
import mingjie.kahoot.gameservice.dto.GameDTO;
import mingjie.kahoot.gameservice.dto.GameUpdateRequest;
import mingjie.kahoot.gameservice.model.PageResult;
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
    public ResponseEntity<GameDTO> createGame(@RequestBody GameCreateRequest request, @RequestParam Long creatorId) {
        GameDTO gameDTO = gameService.createGame(request, creatorId);

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
    public ResponseEntity<PageResult<GameDTO>> listGames(@RequestParam Long userId, @RequestParam String status, @RequestParam int page, @RequestParam int size) {
        PageResult<GameDTO> games = gameService.listGames(userId, status, page, size);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameDTO>> getGames() {
        List<GameDTO> games = gameService.listAllGames();
        return ResponseEntity.ok(games);
    }

}
