public abstract class League implements Matchable {
    //REZA_ASADIPOUR_HW09_Maktab146
    private String nameOfLeague;
    private Club[] clubs;
    private int clubCount;
    private Match[] matches;
    private int matchCount;

    public League(String nameOfLeague) {
        this.nameOfLeague = nameOfLeague;
        this.clubs = new Club[20];
        this.clubCount = 0;
        this.matches = new Match[100];
        this.matchCount = 0;
    }

    public abstract Club findClub(String clubName);

    public abstract void addClub(String clubName);

    public abstract int calculatePoints(int homeScore, int awayScore, boolean isHomeTeam);


    public void sortClubs() {
        for (int i = 0; i < clubCount - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < clubCount - i - 1; j++) {

                if (clubs[j].getPoints() < clubs[j + 1].getPoints() ||
                        (clubs[j].getPoints() == clubs[j + 1].getPoints()
                                && clubs[j].getGoalDifference() < clubs[j + 1].getGoalDifference()
                        )
                ) {
                    Club temporary = clubs[j];
                    clubs[j] = clubs[j + 1];
                    clubs[j + 1] = temporary;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public void displayRankings() {
        System.out.println("\n           ----- " + nameOfLeague + " Rankings -----");
        System.out.printf("%-23s %5s %5s %5s %5s %5s %5s %5s %5s\n",
                "Club", "MP", "W", "D", "L", "GF", "GA", "GD", "Pts"
        );

        for (int i = 0; i < clubCount; i++) {
            clubs[i].displayInfo();
        }
    }

    public void displayMatchHistory() {
        if (matchCount == 0) {
            System.out.println("No matches recorded for " + nameOfLeague);
        } else {
            for (Match match : matches) {
                if (match != null) {
                    match.displayMatch();
                }
            }
        }
    }

    protected void addToLeague(Club club) {
        if (clubCount < clubs.length) {
            clubs[clubCount++] = club;
        } else {
            System.out.println("League is full! Can't add more clubs. ("
                    + clubs.length + "/" + clubs.length + ")"
            );
        }
    }

    protected void addToMatch(Match match) {
        if (matchCount < matches.length) {
            matches[matchCount++] = match;
        } else {
            System.out.println("Match list is full! Cannot add more matches. ("
                    + matches.length + "/" + matches.length + ")"
            );
        }
    }

    public void removeClub(String clubName) {
        int foundIndex = -1;
        for (int i = 0; i < clubCount; i++) {
            if (clubs[i].getClubName().equalsIgnoreCase(clubName)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex < 0) {
            System.out.println("The requested club wasn't found.");
        } else {
            String removedClub = clubs[foundIndex].getClubName();
            for (int i = foundIndex; i < clubCount - 1; i++) {
                clubs[i] = clubs[i + 1];
            }
            clubs[clubCount - 1] = null;
            clubCount--;
            System.out.println("The selected club(" + removedClub + ") removed successfully.");
        }
    }

    public Club getTopScoringClub() {
        if (clubCount == 0) {
            return null;
        } else {
            Club topScoreClub = clubs[0];
            for (int i = 0; i < clubCount; i++) {
                if (clubs[i].getGoalsFor() > topScoreClub.getGoalsFor()) {
                    topScoreClub = clubs[i];
                }
            }
            return topScoreClub;
        }
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Match[] getMatches() {
        return matches;
    }

    public String getNameOfLeague() {
        return nameOfLeague;
    }

    public Club[] getClubs() {
        return clubs;
    }

    public int getClubCount() {
        return clubCount;
    }

}

