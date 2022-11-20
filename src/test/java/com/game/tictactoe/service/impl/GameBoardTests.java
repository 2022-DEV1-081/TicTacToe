package com.game.tictactoe.service.impl;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.domain.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameBoardTests {

    private GameBoard gameBoard;

    @Before
    public void setUp() {
        gameBoard = new GameBoard();
    }

    @Test
    public void shouldSaveInputValueOnGameBoard() {

        gameBoard.setPlayerInPosition(Position.TWO, Player.X);

        assertThat(gameBoard.getPlayerInPosition(Position.TWO)).isEqualTo(Player.X.getValue());
    }

    @Test
    public void getCountPositionsOccupiedOnGameBoard() {

        assertThat(gameBoard.getCountOfPositionsOccupied()).isZero();
    }
}