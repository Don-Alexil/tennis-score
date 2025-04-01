package ro.alexil.tennis.state;

public final class DeuceState implements GameState{

    @Override
    public GameState pointWonBy(char player) {
        return new AdvantageState(player);
    }

}
