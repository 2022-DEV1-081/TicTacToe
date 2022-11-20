package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
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

        assertThat(gameService.playGame(Player.X, 0, 0)).isEqualTo("Player X moved first");
    }

    @Test(expected = InvalidTurnException.class)
    public void shouldThrowInvalidTurnExceptionIfOMovesFirst() {

        gameService.playGame(Player.O, 0, 1);
    }

    @Test
    public void getPositionFromPlayerAndSaveOnGameBoard() {

        gameService.playGame(Player.X, 0, 2);

        assertThat(gameBoard.getValueOnBoard(0, 2)).isEqualTo(Player.X.getValue());
    }
}