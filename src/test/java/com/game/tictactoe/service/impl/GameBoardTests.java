package com.game.tictactoe.service.impl;

import com.game.tictactoe.domain.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameBoardTests {

    @Test
    public void shouldSaveInputValueOnGameBoard() {

        GameBoard gameBoard = new GameBoard();

        gameBoard.setValueOnBoard(0, 1, Player.X);

        assertThat(gameBoard.getValueOnBoard(0, 1)).isEqualTo(Player.X.getValue());
    }
}