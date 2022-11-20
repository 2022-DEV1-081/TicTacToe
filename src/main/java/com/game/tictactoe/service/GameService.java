package com.game.tictactoe.service;

import com.game.tictactoe.exception.InvalidTurnException;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public String playGame(char player) {

        String message = null;
        if (player == 'X') {
            message = "Player X moved first";
        } else if (player == 'O') {
            throw new InvalidTurnException("Player X should move first");
        }
        return message;
    }
}