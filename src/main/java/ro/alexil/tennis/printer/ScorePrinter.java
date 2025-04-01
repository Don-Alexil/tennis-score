package ro.alexil.tennis.printer;

import ro.alexil.tennis.state.GameState;

public interface ScorePrinter {
    String printScore(GameState state);
}
