package com.game.tictactoe.service.impl;

import com.game.tictactoe.domain.Player;
import org.springframework.stereotype.Service;

@Service
public class GameBoard {

    private char[][] board = new char[3][3];

    public void setValueOnBoard(int row, int column, Player player) {
        board[row][column] = player.getValue();
    }

    public char getValueOnBoard(int row, int column) {
        return board[row][column];
    }
}