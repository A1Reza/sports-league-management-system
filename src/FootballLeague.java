public class FootballLeague extends League {
    //REZA_ASADIPOUR_HW09_Maktab146
    public FootballLeague(String nameOfLeague) {
        super(nameOfLeague);
    }

    @Override
    public int calculatePoints(int homeScore, int awayScore, boolean isHomeTeam) {
        if (homeScore == awayScore) return 1;
        if ((homeScore > awayScore && isHomeTeam) || (awayScore > homeScore && !isHomeTeam)) return 3;
        return 0;
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
        System.out.println("Club (" + clubName + ") added to Football League.");
    }



    @Override
    public void recordMatch(String homeTeamName, String awayTeamName, int numberOfGoalsByHomeTeam, int numberOfGoalsByAwayTeam) {
        Club homeFootball = findClub(homeTeamName);
        Club awayFootball = findClub(awayTeamName);

        if (homeFootball == null || awayFootball == null) {
            System.out.println("Invalid club name/names.");
            return;
        }

        homeFootball.addGoalsFor(numberOfGoalsByHomeTeam);
        homeFootball.addGoalsAgainst(numberOfGoalsByAwayTeam);

        awayFootball.addGoalsFor(numberOfGoalsByAwayTeam);
        awayFootball.addGoalsAgainst(numberOfGoalsByHomeTeam);

        int homePoints = calculatePoints(numberOfGoalsByHomeTeam, numberOfGoalsByAwayTeam, true);
        int awayPoints = calculatePoints(numberOfGoalsByHomeTeam, numberOfGoalsByAwayTeam, false);

        homeFootball.addTotalClubPointsInTheLeague(homePoints);
        awayFootball.addTotalClubPointsInTheLeague(awayPoints);

        if (numberOfGoalsByHomeTeam > numberOfGoalsByAwayTeam) {
            homeFootball.incrementWins();
            awayFootball.incrementLosses();
        } else if (numberOfGoalsByHomeTeam < numberOfGoalsByAwayTeam) {
            homeFootball.incrementLosses();
            awayFootball.incrementWins();
        } else {
            homeFootball.incrementDraws();
            awayFootball.incrementDraws();
        }

        addToMatch(new Match(homeTeamName, awayTeamName, numberOfGoalsByHomeTeam, numberOfGoalsByAwayTeam));

        System.out.println("Match recorded: " + homeTeamName + " " + numberOfGoalsByHomeTeam
                + " - " + numberOfGoalsByAwayTeam + " " + awayTeamName
        );
    }
}
