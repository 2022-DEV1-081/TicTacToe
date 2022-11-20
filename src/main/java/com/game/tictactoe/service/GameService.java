package com.game.tictactoe.service;

import com.game.tictactoe.domain.GameResponse;
import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.service.impl.GameBoard;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final int ZERO = 0;
    private final GameBoard gameBoard;

    public GameService(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameResponse playGame(Player player, int row, int column) {

        if (isFirstTurn() && isPlayerO(player)) {
            throw new InvalidTurnException("Player X should move first");
        }
        gameBoard.setPlayerInPosition(row, column, player);
        return new GameResponse("GAME_IN_PROGRESS", getNextPlayer(player), player);
    }

    private boolean isFirstTurn() {
        return gameBoard.getCountOfPositionsOccupied() == ZERO;
    }

    private Player getNextPlayer(Player player) {
        return player == Player.X ? Player.O : Player.X;
    }

    private boolean isPlayerO(Player player) {
        return player == Player.O;
    }
}