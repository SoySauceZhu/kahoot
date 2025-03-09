package mingjie.kahoot.gameservice.service;

import com.github.pagehelper.PageInfo;
import mingjie.kahoot.gameservice.dto.GameCreateRequest;
import mingjie.kahoot.gameservice.dto.GameDTO;
import mingjie.kahoot.gameservice.dto.GameUpdateRequest;
import mingjie.kahoot.gameservice.model.Game;
import mingjie.kahoot.gameservice.model.GameVO;

import java.util.List;

public interface GameService {
    Game createGame(GameCreateRequest request, Long creatorId);

    void updateGame(Long gameId, GameUpdateRequest request, Long userId);

    void deleteGame(Long gameId, Long userId);

    void publishGame(Long gameId, Long userId);

    void endGame(Long gameId, Long userId);

    PageInfo<GameVO> listGames(Long userId, String status, int page, int size);

    List<GameDTO> listAllGames();

//    GameDetailVO getGameDetail(Long gameId, Long userId);
}