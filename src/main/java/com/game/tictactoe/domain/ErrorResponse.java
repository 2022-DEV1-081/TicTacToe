package com.game.tictactoe.domain;

public class ErrorResponse {

    private String warning;

    public ErrorResponse(String warning) {
        this.warning = warning;
    }

    public String getWarning() {
        return warning;
    }
}