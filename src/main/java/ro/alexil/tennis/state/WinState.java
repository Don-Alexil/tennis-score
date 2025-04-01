package ro.alexil.tennis.state;

public record WinState(char player) implements GameState {

    @Override
    public GameState pointWonBy(char winner) {
        return this; // Game is already won, do nothing.
    }
}