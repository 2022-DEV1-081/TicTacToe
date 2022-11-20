package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidTurnException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

    private GameService gameService;

    @Before
    public void setUp() {
        gameService = new GameService();
    }

    @Test
    public void playerXShouldAlwaysGoFirst() {

        assertThat(gameService.playGame(Player.X)).isEqualTo("Player X moved first");
    }

    @Test(expected = InvalidTurnException.class)
    public void shouldThrowInvalidTurnExceptionIfOMovesFirst() {

        gameService.playGame(Player.O);
    }
}