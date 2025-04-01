package ro.alexil.tennis.printer;

import ro.alexil.tennis.state.*;

public class ConsoleScorePrinter implements ScorePrinter {

    @Override
    public String printScore(GameState state) {
        String result =  switch (state) {
            case NormalState normalState ->
                    String.format("Player A : %d / Player B : %d", normalState.getScoreA(), normalState.getScoreB());
            case DeuceState ignored -> "Deuce";
            case AdvantageState advantageState ->
                    String.format("Advantage Player %c", advantageState.player());
            case WinState winState ->
                    String.format("Player %c wins the game", winState.player());
        };
        System.out.println(result);
        return result;
    }
}
