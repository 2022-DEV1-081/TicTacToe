package com.game.tictactoe.controller;

import com.game.tictactoe.domain.GameResponse;
import com.game.tictactoe.domain.Player;
import com.game.tictactoe.domain.Position;
import com.game.tictactoe.exception.InvalidPositionException;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.exception.PositionOccupiedException;
import com.game.tictactoe.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class GameControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameService gameService;

    @Test
    public void playGameAPIShouldGive200Response() throws Exception {

        when(gameService.playGame(Player.X, Position.ONE.getValue()))
                .thenReturn(new GameResponse("GAME_IN_PROGRESS", Player.O, Player.X));

        mvc.perform(post("/tic-tac-toe/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"player\": \"X\", \"position\": 1 }"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldShow403HttpStatusWhenInValidExceptionIsThrown() throws Exception {

        when(gameService.playGame(Player.O, Position.TWO.getValue()))
                .thenThrow(new InvalidTurnException("Player X should move first"));

        mvc.perform(post("/tic-tac-toe/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"player\": \"O\", \"position\": 2 }"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldShow403HttpStatusWhenPositionOccupiedExceptionIsThrown() throws Exception {

        when(gameService.playGame(Player.X, Position.FIVE.getValue()))
                .thenThrow(new PositionOccupiedException("Position %s is already occupied"));

        mvc.perform(post("/tic-tac-toe/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"player\": \"X\", \"position\": 5 }"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldShow403HttpStatusWhenInvalidPositionExceptionIsThrown() throws Exception {

        when(gameService.playGame(Player.X, Position.DEFAULT.getValue()))
                .thenThrow(new InvalidPositionException("Position %s is already occupied"));

        mvc.perform(post("/tic-tac-toe/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"player\": \"X\", \"position\": 0 }"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void resetGameHandlerAPIFound() throws Exception {

        mvc.perform(put("/tic-tac-toe/reset-game")).andExpect(status().isOk());
    }
}