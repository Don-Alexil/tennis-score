package ro.alexil.tennis;

import ro.alexil.tennis.printer.ScorePrinter;
import ro.alexil.tennis.state.GameState;
import ro.alexil.tennis.state.NormalState;
import ro.alexil.tennis.state.WinState;

public class TennisGame {

    private final ScorePrinter scorePrinter;

    public TennisGame(ScorePrinter scorePrinter) {
        this.scorePrinter = scorePrinter;
    }

    /**
     * Processes a sequence of points in a tennis game.
     * <p>
     * - Each character in the input string represents a player winning a point:
     *   - 'A' means Player A wins the point.
     *   - 'B' means Player B wins the point.
     *   - Any other character is ignored.
     * - The game state transitions based on the current score:
     *   - Normal scoring: 0 → 15 → 30 → 40.
     *   - If both players reach 40, the game enters "Deuce."
     *   - From Deuce, a player must gain "Advantage" and then win.
     * - The game stops processing further points once a player has won.
     * - The score is printed after each valid point using the configured `ScorePrinter`.
     *
     * @param input A string containing the sequence of points won by players ('A' or 'B').
     */
    public void computeScore(String input) {
        GameState state = new NormalState();
        for (char c : input.toCharArray()) {
            if (c != 'A' && c != 'B')
                continue;
            state = state.pointWonBy(c);
            scorePrinter.printScore(state);
            if (state instanceof WinState)
                break;
        }
    }
}
