package ro.alexil.tennis.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class NormalStateTest {

    private NormalState normalState;

    @BeforeEach
    void setUp() {
        normalState = new NormalState();
    }

    @Test
    void testInitialScoresAreZero() {
        assertEquals(0, normalState.getScoreA());
        assertEquals(0, normalState.getScoreB());
    }

    @Test
    void testPlayerAWinsPoint() {
        GameState nextState = normalState.pointWonBy('A');
        assertInstanceOf(NormalState.class, nextState);
        NormalState newState = (NormalState) nextState;
        assertEquals(15, newState.getScoreA());
        assertEquals(0, newState.getScoreB());
    }

    @Test
    void testPlayerBWinsPoint() {
        GameState nextState = normalState.pointWonBy('B');
        assertInstanceOf(NormalState.class, nextState);
        NormalState newState = (NormalState) nextState;
        assertEquals(0, newState.getScoreA());
        assertEquals(15, newState.getScoreB());
    }

    @Test
    void testTransitionToDeuce() {
        normalState = (NormalState) normalState.pointWonBy('A');
        normalState = (NormalState) normalState.pointWonBy('A');
        normalState = (NormalState) normalState.pointWonBy('A');
        normalState = (NormalState) normalState.pointWonBy('B');
        normalState = (NormalState) normalState.pointWonBy('B');
        GameState nextState = normalState.pointWonBy('B');
        assertInstanceOf(DeuceState.class, nextState);
    }

    @ParameterizedTest
    @CsvSource({
            "A, A",  // Player A wins
            "B, B"   // Player B wins
    })
    void testTransitionToWinState(char winner, char expectedWinner) {
        // Simulate winning sequence
        normalState = (NormalState) normalState.pointWonBy(winner);
        normalState = (NormalState) normalState.pointWonBy(winner);
        normalState = (NormalState) normalState.pointWonBy(winner);

        GameState nextState = normalState.pointWonBy(winner);

        // Assert correct transition
        assertInstanceOf(WinState.class, nextState);
        assertEquals(expectedWinner, ((WinState) nextState).player());
    }

}