package com.game.tictactoe.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
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