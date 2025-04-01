package ro.alexil.tennis.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class WinStateTest {
    private WinState winState;

    @BeforeEach
    void setUp() {
        winState = new WinState('A'); // Default setup (Player A wins)
    }

    @ParameterizedTest
    @CsvSource({
            "A",  // Player A wins
            "B"   // Player B wins
    })
    void testWinStateAssignsCorrectWinner(char winner) {
        winState = new WinState(winner);
        assertEquals(winner, winState.player());
    }

    @ParameterizedTest
    @CsvSource({
            "A, B",  // Player A won, but Player B tries to win a point
            "B, A"   // Player B won, but Player A tries to win a point
    })
    void testWinStateRemainsUnchangedWhenPointWon(char winner, char opponent) {
        winState = new WinState(winner);

        GameState nextState = winState.pointWonBy(opponent);

        assertInstanceOf(WinState.class, nextState);
        assertEquals(winner, ((WinState) nextState).player());
    }
}