package mingjie.kahoot.gameservice.service.impl;

import com.github.pagehelper.PageInfo;
import mingjie.kahoot.gameservice.dto.GameCreateRequest;
import mingjie.kahoot.gameservice.dto.GameUpdateRequest;
import mingjie.kahoot.gameservice.dto.OptionCreateRequest;
import mingjie.kahoot.gameservice.dto.QuestionCreateRequest;
import mingjie.kahoot.gameservice.mapper.GameMapper;
import mingjie.kahoot.gameservice.mapper.OptionMapper;
import mingjie.kahoot.gameservice.mapper.QuestionMapper;
import mingjie.kahoot.gameservice.model.Game;
import mingjie.kahoot.gameservice.model.Question;
import mingjie.kahoot.gameservice.service.GameService;
import mingjie.kahoot.gameservice.service.OptionService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
public class GameServiceImpl implements GameService {

    final GameMapper gameMapper;
    final QuestionMapper questionMapper;
    final OptionMapper optionMapper;
    final QuestionServiceImpl questionService;
    final OptionService optionService;

    @Autowired
    public GameServiceImpl(GameMapper gameMapper, QuestionMapper questionMapper, OptionMapper optionMapper, QuestionServiceImpl questionService, OptionService optionService) {
        this.gameMapper = gameMapper;
        this.questionMapper = questionMapper;
        this.optionMapper = optionMapper;
        this.questionService = questionService;
        this.optionService = optionService;
    }

    private Game createByCreateRequest(GameCreateRequest gameCreateRequest, Long creatorId) {
        String gameCode = generateUniqueGameCode();
        Game game = new Game();
        game.setTitle(gameCreateRequest.getTitle());
        game.setDescription(gameCreateRequest.getDescription());
        game.setGameCode(gameCode);
        game.setCreatorId(creatorId);
        game.setStatus("draft");
        game.setDeleted(false);
        game.setCreatedAt(now());
        game.setUpdatedAt(now());
        gameMapper.insert(game);

        return game;
    }

    private String generateUniqueGameCode() {
        return "GAME-" + RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }

    @Transactional
    @Override
    public Game createGame(GameCreateRequest gameCreateRequest, Long creatorId) {
        Game game = createByCreateRequest(gameCreateRequest, creatorId);

        for (QuestionCreateRequest questionCreateRequest : gameCreateRequest.getQuestions()) {
            Question question = questionService.createQuestion(questionCreateRequest, game.getId());

            List<Integer> orderList = new ArrayList<>();

            for (OptionCreateRequest optionCreateRequest : questionCreateRequest.getOptionCreateRequests()) {
                if (orderList.contains(optionCreateRequest.getOrder())) {
                    throw new IllegalArgumentException("Duplicate order value: " + optionCreateRequest.getOrder());
                }
                orderList.add(optionCreateRequest.getOrder());
                optionService.createOption(optionCreateRequest, question.getId());
            }
        }
        return game;
    }

    @Transactional
    @Override
    public void updateGame(Long gameId, GameUpdateRequest gameUpdateRequest, Long userId) {
        Game game = gameMapper.findById(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found with id: " + gameId);
        }

        if (!game.getCreatorId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to publish this game");
        }

        if (gameUpdateRequest.getTitle() != null) {
            game.setTitle(gameUpdateRequest.getTitle());
        }
        if (gameUpdateRequest.getDescription() != null) {
            game.setDescription(gameUpdateRequest.getDescription());
        }
        if (gameUpdateRequest.getStatus() != null) {
            game.setStatus(gameUpdateRequest.getStatus());
        }
        if (gameUpdateRequest.getDeleted() != null) {
            game.setDeleted(gameUpdateRequest.getDeleted());
        }

        gameMapper.update(game);
    }

    @Transactional
    @Override
    public void deleteGame(Long gameId, Long userId) {
        Game game = gameMapper.findById(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found with id: " + gameId);
        }

        if (!game.getCreatorId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to publish this game");
        }

        game.setDeleted(true);
        gameMapper.update(game);
    }

    @Transactional
    @Override
    public void publishGame(Long gameId, Long userId) {
        Game game = gameMapper.findById(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found with id: " + gameId);
        }

        if (!game.getCreatorId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to publish this game");
        }

        game.setStatus("published");
        gameMapper.update(game);
    }

    @Transactional
    @Override
    public void endGame(Long gameId, Long userId) {
        Game game = gameMapper.findById(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found with id: " + gameId);
        }

//        if (!game.getCreatorId().equals(userId)) {
//            throw new IllegalArgumentException("User is not authorized to publish this game");
//        }

        game.setStatus("ended");
        gameMapper.update(game);
    }

    @Override
    public PageInfo<Game> getGamePages(Long userId, String status, int page, int size) {
        int offset = (page - 1) * size;
        List<Game> games = gameMapper.findAllByUserIdAndStatus(userId, status, offset, size);
        return new PageInfo<>(games);
    }

    @Override
    public List<Game> listAllGames(Long userId) {
        return gameMapper.findAllById(userId);
    }
}