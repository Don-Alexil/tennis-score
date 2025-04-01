package ro.alexil.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ro.alexil.tennis.printer.ScorePrinter;
import ro.alexil.tennis.state.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TennisGameTest {

    private TennisGame game;
    private ScorePrinter mockPrinter;
    private ArgumentCaptor<GameState> stateCaptor;

    @BeforeEach
    void setUp() {
        mockPrinter = Mockito.mock(ScorePrinter.class);
        stateCaptor = ArgumentCaptor.forClass(GameState.class);
        game = new TennisGame(mockPrinter);
    }

    @Test
    void testGameProgressionWithAccumulator() {
        game.computeScore("ABABAA"); // A wins

        // Capture all printed states
        Mockito.verify(mockPrinter, Mockito.times(6)).printScore(stateCaptor.capture());

        // Get the sequence of states at the moment of print
        List<GameState> printedStates = stateCaptor.getAllValues();

        // Check the correct state transitions
        assertInstanceOf(NormalState.class, printedStates.get(0)); // A: 15-0
        assertInstanceOf(NormalState.class, printedStates.get(1)); // A: 15-15
        assertInstanceOf(NormalState.class, printedStates.get(2)); // A: 30-15
        assertInstanceOf(NormalState.class, printedStates.get(3)); // A: 30-30
        assertInstanceOf(NormalState.class, printedStates.get(4)); // A: 40-30
        assertInstanceOf(WinState.class, printedStates.get(5));    // A wins
    }

    @Test
    void testGameReachesDeuceAndAdvantageWithAccumulator() {
        game.computeScore("ABABABAA"); // 40-40 (Deuce)

        Mockito.verify(mockPrinter, Mockito.times(8)).printScore(stateCaptor.capture());

        List<GameState> printedStates = stateCaptor.getAllValues();

        assertInstanceOf(NormalState.class, printedStates.get(0));  // A: 15-0
        assertInstanceOf(NormalState.class, printedStates.get(1));  // A: 15-15
        assertInstanceOf(NormalState.class, printedStates.get(2));  // A: 30-15
        assertInstanceOf(NormalState.class, printedStates.get(3));  // A: 30-30
        assertInstanceOf(NormalState.class, printedStates.get(4));  // A: 40-30
        assertInstanceOf(DeuceState.class, printedStates.get(5));   // Deuce
        assertInstanceOf(AdvantageState.class, printedStates.get(6)); // Advantage A
        assertInstanceOf(WinState.class, printedStates.get(7));     // A wins
    }

    @Test
    void testIgnoreInvalidCharactersWithAccumulator() {
        game.computeScore("A1B!A B"); // Invalid characters should be ignored

        Mockito.verify(mockPrinter, Mockito.times(4)).printScore(stateCaptor.capture());

        List<GameState> printedStates = stateCaptor.getAllValues();

        assertInstanceOf(NormalState.class, printedStates.get(0)); // A: 15-0
        assertInstanceOf(NormalState.class, printedStates.get(1)); // A: 15-15
        assertInstanceOf(NormalState.class, printedStates.get(2)); // A: 30-15
        assertInstanceOf(NormalState.class, printedStates.get(3)); // A: 30-30
    }

    @Test
    void testGameStopsAfterWinWithAccumulator() {
        game.computeScore("AAAAB"); // A wins

        Mockito.verify(mockPrinter, Mockito.times(4)).printScore(stateCaptor.capture());

        List<GameState> printedStates = stateCaptor.getAllValues();

        assertInstanceOf(NormalState.class, printedStates.get(0)); // A: 15-0
        assertInstanceOf(NormalState.class, printedStates.get(1)); // A: 30-0
        assertInstanceOf(NormalState.class, printedStates.get(2)); // A: 40-0
        assertInstanceOf(WinState.class, printedStates.get(3));    // A wins

        // Ensure the game doesn't print any state after the WinState
        assertEquals(4, printedStates.size());
    }
}
