public class VolleyballLeague extends League {
    //REZA_ASADIPOUR_HW09_Maktab146
    public VolleyballLeague(String nameOfLeague) {
        super(nameOfLeague);
    }

    @Override
    public int calculatePoints(int homeScore, int awayScore, boolean isHomeTeam) {
        boolean winDiagnosis = (homeScore > awayScore && isHomeTeam) || (awayScore > homeScore && !isHomeTeam);
        if (winDiagnosis) {
            // |absolute value|
            if (Math.abs(homeScore - awayScore) >= 2)
                return 3;
            else
                return 2;
        } else {
            if (Math.abs(homeScore - awayScore) == 1)
                return 1;
            else
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
        Club club = new Club(clubName);
        addToLeague(club);
        System.out.println("Club " + clubName + " added to Volleyball League.");
    }


    @Override
    public void recordMatch(String homeTeamName, String awayTeamName, int numberOfSetsWonByHomeTeam, int numberOfSetsWonByAwayTeam) {
        Club homeVolleyball = findClub(homeTeamName);
        Club awayVolleyball = findClub(awayTeamName);

        if (homeVolleyball == null || awayVolleyball == null) {
            System.out.println("Invalid club name/names");
            return;
        }


        homeVolleyball.addGoalsFor(numberOfSetsWonByHomeTeam);
        homeVolleyball.addGoalsAgainst(numberOfSetsWonByAwayTeam);

        awayVolleyball.addGoalsFor(numberOfSetsWonByAwayTeam);
        awayVolleyball.addGoalsAgainst(numberOfSetsWonByHomeTeam);

        int homePoints = calculatePoints(numberOfSetsWonByHomeTeam, numberOfSetsWonByAwayTeam, true);
        int awayPoints = calculatePoints(numberOfSetsWonByHomeTeam, numberOfSetsWonByAwayTeam, false);

        homeVolleyball.addTotalClubPointsInTheLeague(homePoints);
        awayVolleyball.addTotalClubPointsInTheLeague(awayPoints);

        if (numberOfSetsWonByHomeTeam > numberOfSetsWonByAwayTeam) {
            homeVolleyball.incrementWins();
            awayVolleyball.incrementLosses();
        } else {
            homeVolleyball.incrementLosses();
            awayVolleyball.incrementWins();
        }

        addToMatch(new Match(homeTeamName, awayTeamName, numberOfSetsWonByHomeTeam, numberOfSetsWonByAwayTeam));

        System.out.println("Match recorded: " + homeTeamName + " " + numberOfSetsWonByHomeTeam + " - "
                + numberOfSetsWonByAwayTeam + " " + awayTeamName
        );
    }
}
