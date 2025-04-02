package ro.alexil.tennis;

public class TennisGameNaive {

    public static void computeScore(String input) {
        int scoreA = 0, scoreB = 0;
        boolean advantageA = false, advantageB = false;

        for (char point : input.toCharArray()) {
            if (point == 'A') {
                if (scoreA == 40 && !advantageB) {
                    if (advantageA || scoreB < 40) {
                        System.out.println("Player A wins the game");
                        return;
                    }
                    if (scoreB == 40) {
                        advantageA = true;
                        advantageB = false;
                        System.out.println("Advantage Player A");
                    }
                } else if (advantageB) {
                    advantageA = false;
                    advantageB = false;
                    System.out.println("Deuce");
                } else {
                    scoreA = nextScore(scoreA);
                    printScore(scoreA, scoreB);
                }
            } else if (point == 'B') {
                if (scoreB == 40 && !advantageA) {
                    if (advantageB || scoreA < 40) {
                        System.out.println("Player B wins the game");
                        return;
                    }
                    if (scoreA == 40) {
                        advantageB = true;
                        advantageA = false;
                        System.out.println("Advantage Player B");
                    }
                } else if (advantageA) {
                    advantageA = false;
                    advantageB = false;
                    System.out.println("Deuce");
                } else {
                    scoreB = nextScore(scoreB);
                    printScore(scoreA, scoreB);
                }
            }
        }
    }

    private static int nextScore(int score) {
        if (score == 0) return 15;
        if (score == 15) return 30;
        if (score == 30) return 40;
        return score;
    }

    private static void printScore(int scoreA, int scoreB) {
        System.out.println("Player A : " + scoreA + " / Player B : " + scoreB);
    }

    public static void main(String[] args) {
        String input = "ABABAA";
        computeScore(input);
    }
}
