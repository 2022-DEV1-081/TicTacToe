package com.game.tictactoe.domain;

import lombok.Getter;

@Getter
public class GameResponse {

    private String result;
    private String status;
    private Player nextPlayer;
    private Player currentPlayer;

    public GameResponse(String result, String status) {
        this.result = result;
        this.status = status;
    }

    public GameResponse(String status, Player nextPlayer, Player currentPlayer) {
        this.status = status;
        this.nextPlayer = nextPlayer;
        this.currentPlayer = currentPlayer;
    }
}