public class Match {
    //REZA_ASADIPOUR_HW09_Maktab146
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;

    public Match(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void displayMatch() {
        System.out.printf("Match: %s vs %s | Score: %d - %d%n",
                homeTeam, awayTeam, homeScore, awayScore
        );
    }
}
