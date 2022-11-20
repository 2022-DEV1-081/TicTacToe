package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidTurnException;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public String playGame(Player player) {

        String message = null;
        if (player == Player.X) {
            message = "Player X moved first";
        } else if (isPlayerO(player)) {
            throw new InvalidTurnException("Player X should move first");
        }
        return message;
    }

    private boolean isPlayerO(Player player) {
        return player == Player.O;
    }
}