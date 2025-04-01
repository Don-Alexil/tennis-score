package ro.alexil.tennis.printer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ro.alexil.tennis.state.AdvantageState;
import ro.alexil.tennis.state.DeuceState;
import ro.alexil.tennis.state.NormalState;
import ro.alexil.tennis.state.WinState;

class ConsoleScorePrinterTest {
    private ConsoleScorePrinter printer;

    @BeforeEach
    void setUp() {
        printer = new ConsoleScorePrinter();
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 'Player A : 0 / Player B : 0'",
            "15, 30, 'Player A : 15 / Player B : 30'",
            "40, 15, 'Player A : 40 / Player B : 15'"
    })
    void testPrintNormalState(int scoreA, int scoreB, String expectedOutput) {
        NormalState state = new NormalState(scoreA, scoreB); // Assuming a constructor
        String result = printer.printScore(state);
        assertEquals(expectedOutput, result);
    }

    @ParameterizedTest
    @CsvSource({
            "A, 'Advantage Player A'",
            "B, 'Advantage Player B'"
    })
    void testPrintAdvantageState(char player, String expectedOutput) {
        AdvantageState state = new AdvantageState(player);
        String result = printer.printScore(state);
        assertEquals(expectedOutput, result);
    }

    @ParameterizedTest
    @CsvSource({
            "A, 'Player A wins the game'",
            "B, 'Player B wins the game'"
    })
    void testPrintWinState(char player, String expectedOutput) {
        WinState state = new WinState(player);
        String result = printer.printScore(state);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testPrintDeuceState() {
        DeuceState state = new DeuceState();
        String result = printer.printScore(state);
        assertEquals("Deuce", result);
    }
}
