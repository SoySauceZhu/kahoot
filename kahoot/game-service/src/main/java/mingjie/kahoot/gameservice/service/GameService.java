package mingjie.kahoot.gameservice.service;

import mingjie.kahoot.gameservice.dto.GameCreateRequest;
import mingjie.kahoot.gameservice.dto.GameDTO;
import mingjie.kahoot.gameservice.dto.GameUpdateRequest;
import mingjie.kahoot.gameservice.model.PageResult;

import java.util.List;

public interface GameService {
    GameDTO createGame(GameCreateRequest request, Long creatorId);

    void updateGame(Long gameId, GameUpdateRequest request, Long userId);

    void deleteGame(Long gameId, Long userId);

    void publishGame(Long gameId, Long userId);

    void endGame(Long gameId, Long userId);

    PageResult<GameDTO> listGames(Long userId, String status, int page, int size);

    List<GameDTO> listAllGames();

//    GameDetailVO getGameDetail(Long gameId, Long userId);
}