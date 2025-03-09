package mingjie.kahoot.gameservice.util;

import mingjie.kahoot.gameservice.dto.GameDTO;
import mingjie.kahoot.gameservice.model.Game;

public class GameConverter {
    public static GameDTO convertToDTO(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setGameCode(game.getGameCode());
        dto.setStatus(game.getStatus());
        dto.setCreatedAt(game.getCreatedAt());
        return dto;
    }
}
