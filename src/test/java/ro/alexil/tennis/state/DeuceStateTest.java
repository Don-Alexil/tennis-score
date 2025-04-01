package ro.alexil.tennis.state;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class DeuceStateTest {

    @ParameterizedTest
    @CsvSource({
            "A, A",  // Player A has advantage and wins → Player A should win
            "B, B"   // Player B has advantage and wins → Player B should win
    })
    void testDeuceToAdvantageState(char advantagePlayer, char expectedAdvantagePlayer) {
        DeuceState deuceState = new DeuceState();

        GameState nextState = deuceState.pointWonBy(advantagePlayer);

        assertInstanceOf(AdvantageState.class, nextState);
        assertEquals(expectedAdvantagePlayer, ((AdvantageState) nextState).player());
    }
}
