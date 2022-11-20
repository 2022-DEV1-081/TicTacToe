package com.game.tictactoe.controller;

import com.game.tictactoe.domain.ErrorResponse;
import com.game.tictactoe.domain.GameResponse;
import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "/tic-tac-toe/play/{player}/{position}")
    public ResponseEntity<GameResponse> playGameHandler(@PathVariable(name = "player") Player player,
                                                        @PathVariable(name = "position") int position) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.playGame(player, position));
    }

    @ExceptionHandler(value = InvalidTurnException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTurnException(InvalidTurnException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(ex.getMessage()));
    }
}