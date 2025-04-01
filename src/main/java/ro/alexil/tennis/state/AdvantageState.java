package ro.alexil.tennis.state;

public record AdvantageState(char player) implements GameState {

    @Override
    public GameState pointWonBy(char winner) {
        return (winner == player) ? new WinState(player) : new DeuceState();
    }
}
