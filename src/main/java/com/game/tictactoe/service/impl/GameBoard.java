package com.game.tictactoe.service.impl;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.domain.Position;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Arrays;

@Service
public class GameBoard {

    public static final int TOTAL_POSITIONS_ON_BOARD = 9;
    private static final int EMPTY_POSITION_ON_BOARD = 0;
    private char[][] board;

    public void initializeGameBoard() {
        board = new char[3][3];
    }

    public void setPlayerInPosition(Position position, Player player) {
        board[position.getRow()][position.getColumn()] = player.getValue();
    }

    public char getPlayerInPosition(Position position) {
        return board[position.getRow()][position.getColumn()];
    }

    public int getCountOfPositionsOccupied() {

        return (int) Arrays.stream(board)
                .map(CharBuffer::wrap)
                .flatMapToInt(CharBuffer::chars)
                .filter(position -> position != EMPTY_POSITION_ON_BOARD)
                .count();
    }

    public boolean isBoardFull() {
        return getCountOfPositionsOccupied() == TOTAL_POSITIONS_ON_BOARD;
    }

    public boolean isAnyOneOfThreeRowsOccupiedBySamePlayer() {
        return isFirstRowOccupiedBySamePlayer() || isSecondRowOccupiedBySamePlayer() || isThirdRowOccupiedBySamePlayer();
    }

    public boolean isAnyOfThreeColumnsOccupiedBySamePlayer() {
        return isFirstColumnOccupiedBySamePlayer() || isSecondColumnOccupiedBySamePlayer() || isThirdColumnOccupiedBySamePlayer();
    }

    public boolean isAnyOfTwoDiagonalOccupiedBySamePlayer() {
        return isFirstDiagonalOccupiedBySamePlayer() || isSecondDiagonalOccupiedBySamePlayer();
    }

    protected boolean isFirstRowOccupiedBySamePlayer() {

        return (getPlayerInPosition(Position.ONE) != EMPTY_POSITION_ON_BOARD) &&
                (getPlayerInPosition(Position.ONE) == getPlayerInPosition(Position.TWO)
                        && getPlayerInPosition(Position.TWO) == getPlayerInPosition(Position.THREE));

    }

    protected boolean isSecondRowOccupiedBySamePlayer() {

        return (getPlayerInPosition(Position.FOUR) != EMPTY_POSITION_ON_BOARD) &&
                (getPlayerInPosition(Position.FOUR) == getPlayerInPosition(Position.FIVE)
                        && getPlayerInPosition(Position.FIVE) == getPlayerInPosition(Position.SIX));
    }

    protected boolean isThirdRowOccupiedBySamePlayer() {

        return (getPlayerInPosition(Position.SEVEN) != EMPTY_POSITION_ON_BOARD) &&
                (getPlayerInPosition(Position.SEVEN) == getPlayerInPosition(Position.EIGHT)
                        && getPlayerInPosition(Position.EIGHT) == getPlayerInPosition(Position.NINE));
    }

    protected boolean isFirstColumnOccupiedBySamePlayer() {

        return (getPlayerInPosition(Position.ONE) != EMPTY_POSITION_ON_BOARD) &&
                (getPlayerInPosition(Position.ONE) == getPlayerInPosition(Position.FOUR)
                        && getPlayerInPosition(Position.FOUR) == getPlayerInPosition(Position.SEVEN));
    }

    protected boolean isSecondColumnOccupiedBySamePlayer() {

        return (getPlayerInPosition(Position.TWO) != EMPTY_POSITION_ON_BOARD) &&
                (getPlayerInPosition(Position.TWO) == getPlayerInPosition(Position.FIVE)
                        && getPlayerInPosition(Position.FIVE) == getPlayerInPosition(Position.EIGHT));
    }

    protected boolean isThirdColumnOccupiedBySamePlayer() {

        return (getPlayerInPosition(Position.THREE) != EMPTY_POSITION_ON_BOARD) &&
                (getPlayerInPosition(Position.THREE) == getPlayerInPosition(Position.SIX)
                        && getPlayerInPosition(Position.SIX) == getPlayerInPosition(Position.NINE));
    }

    protected boolean isFirstDiagonalOccupiedBySamePlayer() {

        return (getPlayerInPosition(Position.ONE) != EMPTY_POSITION_ON_BOARD) &&
                (getPlayerInPosition(Position.ONE) == getPlayerInPosition(Position.FIVE)
                        && getPlayerInPosition(Position.FIVE) == getPlayerInPosition(Position.NINE));
    }

    protected boolean isSecondDiagonalOccupiedBySamePlayer() {

        return (getPlayerInPosition(Position.THREE) != EMPTY_POSITION_ON_BOARD) &&
                (getPlayerInPosition(Position.THREE) == getPlayerInPosition(Position.FIVE)
                        && getPlayerInPosition(Position.FIVE) == getPlayerInPosition(Position.SEVEN));
    }
}