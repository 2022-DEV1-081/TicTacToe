package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidTurnException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

    @Test
    public void playerXShouldAlwaysGoFirst() {

        GameService gameService = new GameService();

        assertThat(gameService.playGame(Player.X)).isEqualTo("Player X moved first");
    }

    @Test(expected = InvalidTurnException.class)
    public void shouldThrowInvalidTurnExceptionIfOMovesFirst() {

        GameService gameService = new GameService();

        gameService.playGame(Player.O);
    }
}