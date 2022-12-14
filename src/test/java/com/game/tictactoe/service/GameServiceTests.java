package com.game.tictactoe.service;

import com.game.tictactoe.domain.GameStatus;
import com.game.tictactoe.domain.Player;
import com.game.tictactoe.domain.Position;
import com.game.tictactoe.exception.InvalidPositionException;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.exception.PositionOccupiedException;
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

    @Test(expected = PositionOccupiedException.class)
    public void shouldThrowPositionOccupiedExceptionIfPlayerPlaysOnAlreadyOccupiedPosition() {

        gameService.playGame(Player.X, Position.TWO.getValue());
        gameService.playGame(Player.O, Position.TWO.getValue());
    }

    @Test(expected = InvalidPositionException.class)
    public void shouldThrowInvalidPositionExceptionIfInputPositionIsNotInRangeOf1to9() {

        gameService.playGame(Player.X, Position.DEFAULT.getValue());
    }

    @Test
    public void shouldDeclareWinnerIfAnyOneOfThreeRowsIsFilledBySamePlayer() {

        gameService.playGame(Player.X, Position.THREE.getValue());
        gameService.playGame(Player.O, Position.FOUR.getValue());
        gameService.playGame(Player.X, Position.TWO.getValue());
        gameService.playGame(Player.O, Position.NINE.getValue());

        assertThat(gameService.playGame(Player.X, Position.ONE.getValue()).getStatus()).isEqualTo(GameStatus.GAME_OVER.getStatus());
    }

    @Test
    public void shouldDeclareWinnerIfAnyOneOfThreeColumnsIsFilledBySamePlayer() {

        gameService.playGame(Player.X, Position.TWO.getValue());
        gameService.playGame(Player.O, Position.THREE.getValue());
        gameService.playGame(Player.X, Position.ONE.getValue());
        gameService.playGame(Player.O, Position.SIX.getValue());
        gameService.playGame(Player.X, Position.FOUR.getValue());

        assertThat(gameService.playGame(Player.O, Position.NINE.getValue()).getStatus()).isEqualTo(GameStatus.GAME_OVER.getStatus());
    }

    @Test
    public void shouldDeclareWinnerIfAnyOneOfTwoDiagonalIsFilledBySamePlayer() {

        gameService.playGame(Player.X, Position.ONE.getValue());
        gameService.playGame(Player.O, Position.THREE.getValue());
        gameService.playGame(Player.X, Position.FIVE.getValue());
        gameService.playGame(Player.O, Position.SIX.getValue());

        assertThat(gameService.playGame(Player.X, Position.NINE.getValue()).getStatus()).isEqualTo(GameStatus.GAME_OVER.getStatus());
    }

    @Test
    public void shouldDeclareTieIfAllPositionsAreFilledWithoutAnyPlayerFillingConsecutiveRowsColumnsOrDiagonals() {

        gameService.playGame(Player.X, Position.ONE.getValue());
        gameService.playGame(Player.O, Position.THREE.getValue());
        gameService.playGame(Player.X, Position.TWO.getValue());
        gameService.playGame(Player.O, Position.FIVE.getValue());
        gameService.playGame(Player.X, Position.SEVEN.getValue());
        gameService.playGame(Player.O, Position.EIGHT.getValue());
        gameService.playGame(Player.X, Position.NINE.getValue());
        gameService.playGame(Player.O, Position.FOUR.getValue());

        assertThat(gameService.playGame(Player.X, Position.SIX.getValue()).getResult()).isEqualTo("Game is a Tie");
    }

    @Test
    public void resetGameOnceWinnerIsIdentified() {

        gameService.playGame(Player.X, Position.TWO.getValue());
        gameService.playGame(Player.O, Position.THREE.getValue());
        gameService.playGame(Player.X, Position.ONE.getValue());
        gameService.playGame(Player.O, Position.SIX.getValue());
        gameService.playGame(Player.X, Position.FOUR.getValue());

        assertThat(gameService.playGame(Player.O, Position.NINE.getValue()).getStatus()).isEqualTo(GameStatus.GAME_OVER.getStatus());
        assertThat(gameService.playGame(Player.X, Position.ONE.getValue()).getStatus()).isEqualTo(GameStatus.GAME_IN_PROGRESS.getStatus());
    }

    @Test
    public void resetGameAfterTie() {

        gameService.playGame(Player.X, Position.ONE.getValue());
        gameService.playGame(Player.O, Position.THREE.getValue());
        gameService.playGame(Player.X, Position.TWO.getValue());
        gameService.playGame(Player.O, Position.FIVE.getValue());
        gameService.playGame(Player.X, Position.SEVEN.getValue());
        gameService.playGame(Player.O, Position.EIGHT.getValue());
        gameService.playGame(Player.X, Position.NINE.getValue());
        gameService.playGame(Player.O, Position.FOUR.getValue());

        assertThat(gameService.playGame(Player.X, Position.SIX.getValue()).getResult()).isEqualTo("Game is a Tie");
        assertThat(gameService.playGame(Player.X, Position.ONE.getValue()).getStatus()).isEqualTo(GameStatus.GAME_IN_PROGRESS.getStatus());
    }
}