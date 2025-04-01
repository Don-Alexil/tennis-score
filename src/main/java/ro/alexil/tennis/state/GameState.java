package ro.alexil.tennis.state;

public sealed interface GameState permits NormalState,
        DeuceState, AdvantageState, WinState {

    GameState pointWonBy(char player);
}