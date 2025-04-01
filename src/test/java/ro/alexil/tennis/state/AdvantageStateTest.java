package ro.alexil.tennis.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class AdvantageStateTest {
    private AdvantageState advantageState;

    @ParameterizedTest
    @CsvSource({
            "A, A",  // Player A has advantage and wins → Player A should win
            "B, B"   // Player B has advantage and wins → Player B should win
    })
    void testAdvantageToWinState(char advantagePlayer, char expectedWinner) {
        advantageState = new AdvantageState(advantagePlayer);

        GameState nextState = advantageState.pointWonBy(advantagePlayer);

        assertInstanceOf(WinState.class, nextState);
        assertEquals(expectedWinner, ((WinState) nextState).player());
    }

    @ParameterizedTest
    @CsvSource({
            "A, B",  // Player A has advantage but loses → Back to deuce
            "B, A"   // Player B has advantage but loses → Back to deuce
    })
    void testAdvantageToDeuceState(char advantagePlayer, char opponent) {
        advantageState = new AdvantageState(advantagePlayer);

        GameState nextState = advantageState.pointWonBy(opponent);

        assertInstanceOf(DeuceState.class, nextState);
    }
}
