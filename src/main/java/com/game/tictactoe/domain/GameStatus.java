package com.game.tictactoe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameStatus {

    GAME_OVER("GAME_OVER"),
    GAME_IN_PROGRESS("GAME_IN_PROGRESS");
    private final String status;
}