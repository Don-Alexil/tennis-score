package ro.alexil.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ro.alexil.tennis.printer.ConsoleScorePrinter;
import ro.alexil.tennis.printer.ScorePrinter;
import ro.alexil.tennis.state.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisGameIntegrationTest {
    private TennisGame game;
    private List<String> output; // Accumulator for printed scores

    @BeforeEach
    void setUp() {
        output = new ArrayList<>();
        ScorePrinter printer = this::printScore;
        game = new TennisGame(printer);
    }

    private String printScore(GameState state) {
        String result = new ConsoleScorePrinter().printScore(state);
        output.add(result);
        return result;
    }

    @ParameterizedTest
    @MethodSource("inputGames")
    void testGameWithConsoleLikeOutput(String input, String[] expectedOutputs) {
        System.out.printf("Game score for %s\n", input);
        game.computeScore(input);
        assertEquals(List.of(expectedOutputs), output);
    }

    static Stream<Arguments> inputGames() {
        return Stream.of(
            Arguments.of("AAAA", new String[] {
                    "Player A : 15 / Player B : 0",
                    "Player A : 30 / Player B : 0",
                    "Player A : 40 / Player B : 0",
                    "Player A wins the game" }),
            Arguments.of("ABABAA", new String[] {
                    "Player A : 15 / Player B : 0",
                    "Player A : 15 / Player B : 15",
                    "Player A : 30 / Player B : 15",
                    "Player A : 30 / Player B : 30",
                    "Player A : 40 / Player B : 30",
                    "Player A wins the game"
            }),
            Arguments.of("ABABABAA", new String[] {
                    "Player A : 15 / Player B : 0",
                    "Player A : 15 / Player B : 15",
                    "Player A : 30 / Player B : 15",
                    "Player A : 30 / Player B : 30",
                    "Player A : 40 / Player B : 30",
                    "Deuce",
                    "Advantage Player A",
                    "Player A wins the game"
            })

        );
    }

}
