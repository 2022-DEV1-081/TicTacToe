package com.game.tictactoe.controller;

import com.game.tictactoe.domain.ErrorResponse;
import com.game.tictactoe.domain.GameResponse;
import com.game.tictactoe.domain.Player;
import com.game.tictactoe.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tic-tac-toe")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Play Tic Tac Toe Game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully played the Turn",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GameResponse.class))}),
            @ApiResponse(responseCode = "409", description = "Invalid Position parameter",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/play/{player}/{position}")
    public ResponseEntity<GameResponse> playGameHandler(@PathVariable(name = "player") Player player,
                                                        @PathVariable(name = "position") int position) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.playGame(player, position));
    }

    @Operation(summary = "Reset Tic Tac Toe Game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reset Successful")})
    @PutMapping(value = "/reset-game")
    public ResponseEntity<String> resetGameHandler() {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.resetGame());
    }
}