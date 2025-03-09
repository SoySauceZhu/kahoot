package mingjie.kahoot.gameservice.util;

import mingjie.kahoot.gameservice.dto.GameDTO;
import mingjie.kahoot.gameservice.model.Game;
import mingjie.kahoot.gameservice.model.GameVO;

public class GameConverter {
    @Deprecated
    public static GameDTO convertToDTO(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setGameCode(game.getGameCode());
        dto.setStatus(game.getStatus());
        dto.setCreatedAt(game.getCreatedAt());
        return dto;
    }

    public static GameVO convertToVO(Game game) {
            GameVO vo = new GameVO();
            vo.setTitle(game.getTitle());
            vo.setGameCode(game.getGameCode());
            vo.setStatus(game.getStatus());
            vo.setCreatedAt(game.getCreatedAt());
            return vo;
        }
}
