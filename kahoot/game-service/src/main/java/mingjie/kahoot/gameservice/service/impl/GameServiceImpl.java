package mingjie.kahoot.gameservice.service.impl;

import com.github.pagehelper.PageInfo;
import mingjie.kahoot.gameservice.dto.*;
import mingjie.kahoot.gameservice.mapper.GameMapper;
import mingjie.kahoot.gameservice.mapper.OptionMapper;
import mingjie.kahoot.gameservice.mapper.QuestionMapper;
import mingjie.kahoot.gameservice.model.Game;
import mingjie.kahoot.gameservice.model.GameVO;
import mingjie.kahoot.gameservice.model.Option;
import mingjie.kahoot.gameservice.model.Question;
import mingjie.kahoot.gameservice.service.GameService;
import mingjie.kahoot.gameservice.util.GameConverter;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Service
public class GameServiceImpl implements GameService {

    final GameMapper gameMapper;

    final QuestionMapper questionMapper;

    final OptionMapper optionMapper;

    @Autowired
    public GameServiceImpl(GameMapper gameMapper, QuestionMapper questionMapper, OptionMapper optionMapper) {
        this.gameMapper = gameMapper;
        this.questionMapper = questionMapper;
        this.optionMapper = optionMapper;
    }

    public Game createByCreateRequest(GameCreateRequest gameCreateRequest, Long creatorId) {
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


    @Transactional
    @Override
    public GameDTO createGame(GameCreateRequest gameCreateRequest, Long creatorId) {

        Game game = createByCreateRequest(gameCreateRequest, creatorId);

        for (QuestionCreateRequest questionCreateRequest : gameCreateRequest.getQuestions()) {

            List<Integer> orderList = new ArrayList<>();
            Question question = new Question();
            question.setGameId(game.getId());
            question.setContent(questionCreateRequest.getContent());
            question.setTimeLimit(questionCreateRequest.getTimeLimit());
            question.setCorrectAnswer(questionCreateRequest.getCorrectAnswer());
            question.setCreatedAt(now());
            question.setUpdatedAt(now());
            questionMapper.insert(question);


            for (OptionCreateRequest optionCreateRequest : questionCreateRequest.getOptionCreateRequests()) {
                if (orderList.contains(optionCreateRequest.getOrder())) {
                    throw new IllegalArgumentException("Duplicate order value: " + optionCreateRequest.getOrder());
                }
                orderList.add(optionCreateRequest.getOrder());
                Option option = new Option();
                option.setQuestionId(question.getId());
                option.setContent(optionCreateRequest.getContent());
                option.setOrder(optionCreateRequest.getOrder());

                optionMapper.insert(option);
            }
        }

        return GameConverter.convertToDTO(game);
    }

    private String generateUniqueGameCode() {
        return "GAME-" + RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }

    @Override
    public void updateGame(Long gameId, GameUpdateRequest gameUpdateRequest, Long userId) {
        Game game = gameMapper.findById(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found with id: " + gameId);
        }

        if (!game.getCreatorId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to publish this game");
        }

        game.setTitle(gameUpdateRequest.getTitle());
        game.setDescription(gameUpdateRequest.getDescription());
        game.setStatus(gameUpdateRequest.getStatus());
        game.setDeleted(gameUpdateRequest.getDeleted());

        gameMapper.update(game);
    }

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


    @Override
    public void endGame(Long gameId, Long userId) {
        Game game = gameMapper.findById(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found with id: " + gameId);
        }

        if (!game.getCreatorId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to publish this game");
        }

        game.setStatus("ended");
        gameMapper.update(game);
    }

    @Override
    public PageInfo<GameVO> listGames(Long userId, String status, int page, int size) {
        // TODO
        return null;
    }

    @Override
    public List<GameDTO> listAllGames() {
        return gameMapper.findAll().stream().map(GameConverter::convertToDTO).collect(Collectors.toList());
    }
}
