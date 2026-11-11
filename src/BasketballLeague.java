public class BasketballLeague extends League {
    //REZA_ASADIPOUR_HW09_Maktab146
    public BasketballLeague(String nameOfLeague) {
        super(nameOfLeague);
    }

    @Override
    public int calculatePoints(int homeScore, int awayScore, boolean isHomeTeam) {
        boolean winDiagnosis = (homeScore > awayScore && isHomeTeam) || (awayScore > homeScore && !isHomeTeam);
        int bonus = Math.abs(homeScore - awayScore) > 20 ? 1 : 0;
        if (winDiagnosis) {
            return 2 + bonus;
        } else {
            return 0;
        }
    }

    @Override
    public Club findClub(String clubName) {
        for (Club club : getClubs()) {
            if (club != null && club.getClubName().equalsIgnoreCase(clubName))
                return club;
        }
        return null;
    }

    @Override
    public void addClub(String clubName) {
        if (findClub(clubName) != null) {
            System.out.println("Club already exists!");
            return;
        }
        Club basketballClub = new Club(clubName);
        addToLeague(basketballClub);
        System.out.println("Club " + clubName + " added to Basketball League.");
    }

    @Override
    public void recordMatch(String homeTeamName, String awayTeamName, int numberOfSetsWonByHomeTeam, int numberOfSetsWonByAwayTeam) {
        Club homeTeamBasketball = findClub(homeTeamName);
        Club awayTeamBasketball = findClub(awayTeamName);

        if (homeTeamBasketball == null || awayTeamBasketball == null) {
            System.out.println("Invalid club name/names");
            return;
        }


        homeTeamBasketball.addGoalsFor(numberOfSetsWonByHomeTeam);
        homeTeamBasketball.addGoalsAgainst(numberOfSetsWonByAwayTeam);

        awayTeamBasketball.addGoalsFor(numberOfSetsWonByAwayTeam);
        awayTeamBasketball.addGoalsAgainst(numberOfSetsWonByHomeTeam);

        int homePoints = calculatePoints(numberOfSetsWonByHomeTeam, numberOfSetsWonByAwayTeam, true);
        int awayPoints = calculatePoints(numberOfSetsWonByHomeTeam, numberOfSetsWonByAwayTeam, false);

        homeTeamBasketball.addTotalClubPointsInTheLeague(homePoints);
        awayTeamBasketball.addTotalClubPointsInTheLeague(awayPoints);

        if (numberOfSetsWonByHomeTeam > numberOfSetsWonByAwayTeam) {
            homeTeamBasketball.incrementWins();
            awayTeamBasketball.incrementLosses();
        } else {
            homeTeamBasketball.incrementLosses();
            awayTeamBasketball.incrementWins();
        }

        addToMatch(new Match(homeTeamName, awayTeamName, numberOfSetsWonByHomeTeam, numberOfSetsWonByAwayTeam));

        System.out.println("Match recorded: " + homeTeamName + " " + numberOfSetsWonByHomeTeam + " - "
                + numberOfSetsWonByAwayTeam + " " + awayTeamName
        );
    }
}
