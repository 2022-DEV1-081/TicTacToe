package com.game.tictactoe.exception;

public class PositionOccupiedException extends RuntimeException {

    public PositionOccupiedException(String message) {
        super(message);
    }
}