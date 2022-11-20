package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.domain.Position;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.service.impl.GameBoard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

    private GameService gameService;
    private GameBoard gameBoard;

    @Before
    public void setUp() {
        gameBoard = new GameBoard();
        gameService = new GameService(gameBoard);
    }

    @Test
    public void playerXShouldAlwaysGoFirst() {

        assertThat(gameService.playGame(Player.X, Position.ONE.getValue()).getCurrentPlayer()).isEqualTo(Player.X);
    }

    @Test(expected = InvalidTurnException.class)
    public void shouldThrowInvalidTurnExceptionIfOMovesFirst() {

        gameService.playGame(Player.O, Position.TWO.getValue());
    }

    @Test
    public void getPositionFromPlayerAndSaveOnGameBoard() {

        gameService.playGame(Player.X, Position.THREE.getValue());

        assertThat(gameBoard.getPlayerInPosition(Position.THREE)).isEqualTo(Player.X.getValue());
    }

    @Test(expected = InvalidTurnException.class)
    public void shouldThrowInvalidTurnExceptionIfSamePlayerPlaysConsecutiveTurns() {

        gameService.playGame(Player.X, Position.THREE.getValue());
        gameService.playGame(Player.X, Position.TWO.getValue());
    }
}