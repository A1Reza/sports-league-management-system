public class Club implements Rankable {
    //REZA_ASADIPOUR_HW09_Maktab146
    private String clubName;
    private int totalClubPointsInTheLeague;
    private int wins;
    private int draws;
    private int losses;
    private int goalsFor;
    private int goalsAgainst;

    public Club(String clubName) {
        setClubName(clubName);
        this.totalClubPointsInTheLeague = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        if (clubName != null && !clubName.trim().isEmpty()) {
            this.clubName = clubName;
        } else {
            System.out.println("Invalid Name! ClubName can't be null or empty");
        }
    }

    public int getTotalClubPointsInTheLeague() {
        return totalClubPointsInTheLeague;
    }

    public void setTotalClubPointsInTheLeague(int totalClubPointsInTheLeague) {
        this.totalClubPointsInTheLeague = totalClubPointsInTheLeague;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        if (losses > 0) {
            this.losses = losses;
        } else {
            System.out.println("Invalid count of losses! losses can't be negative");
        }
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }


    public void addGoalsFor(int goals) {
        goalsFor += goals;
    }

    public void addGoalsAgainst(int goals) {
        goalsAgainst += goals;
    }

    public void incrementWins() {
        wins++;
    }

    public void incrementDraws() {
        draws++;
    }

    public void incrementLosses() {
        losses++;
    }

    public void addTotalClubPointsInTheLeague(int points) {
        totalClubPointsInTheLeague += points;
    }


    @Override
    public int getPoints() {
        return totalClubPointsInTheLeague;
    }

    @Override
    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    public int getTotalMatchesClubPlayed() {
        return wins + draws + losses;
    }

    public void displayInfo() {
        System.out.printf("%-23s %5s %5s %5s %5s %5s %5s %5s %5s\n",
                clubName,getTotalMatchesClubPlayed() , wins, draws, losses, goalsFor, goalsAgainst,
                getGoalDifference(), totalClubPointsInTheLeague
        );
    }
}
