package ro.alexil.tennis.state;

public final class NormalState implements GameState {

    private final int scoreA;
    private final int scoreB;

    public NormalState() {
        this(0, 0);
    }

    public NormalState(int scoreA, int scoreB) {
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public int getScoreA() { return scoreA; }
    public int getScoreB() { return scoreB; }

    @Override
    public GameState pointWonBy(char player) {
        int newScoreA = scoreA, newScoreB = scoreB;
        if (player == 'A') {
            if(scoreA == 40 && scoreB < 40) {
                return new WinState('A');
            }
            newScoreA = nextScore(scoreA);
        } else if(player == 'B'){
            if(scoreB == 40 && scoreA < 40) {
                return new WinState('B');
            }
            newScoreB = nextScore(scoreB);
        }

        if (newScoreA == 40 && newScoreB == 40) {
            return new DeuceState();
        }
        return new NormalState(newScoreA, newScoreB);
    }

    public int nextScore(int currentScore) {
        return switch (currentScore) {
            case 0 -> 15;
            case 15 -> 30;
            default -> 40;
        };
    }

}
