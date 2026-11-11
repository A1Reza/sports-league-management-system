import java.util.Scanner;

// REZA_ASADIPOUR_HW09_Maktab146
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("<<Sports League Management System – Phase 2>>\n");

        League footballLeague1 = new FootballLeague("Football League");
        League volleyballLeague1 = new VolleyballLeague("Volleyball League");
        League basketballLeague1 = new BasketballLeague("Basketball League");

        int choice;
        do {
            System.out.println("\n------ Sports League Management System Menu ------");
            System.out.println("""
                    1.  Add Club to Football League
                    2.  Add Club to Volleyball League
                    3.  Add Club to Basketball League
                    
                    4.  Record Football Match
                    5.  Record Volleyball Match
                    6.  Record Basketball Match
                    
                    7.  Remove Club from League
                    
                    8.  Display All League Rankings
                    9.  Show Match History
                    10. Show Top Scoring Club per League
                    11. Show Total Points of All Clubs
                    
                    12. Exit
                    """
            );

            System.out.print("Please enter your choice number: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Please enter the Football club's name: ");
                    footballLeague1.addClub(sc.nextLine());
                }

                case 2 -> {
                    System.out.print("Please enter the Volleyball club's name: ");
                    volleyballLeague1.addClub(sc.nextLine());
                }

                case 3 -> {
                    System.out.print("Please enter the Basketball club's name: ");
                    basketballLeague1.addClub(sc.nextLine());
                }


                case 4 -> {
                    System.out.print("Please enter the Football Home team's name: ");
                    String homeTFN = sc.nextLine();
                    System.out.print("Please enter the Football Away team's name: ");
                    String awayTFN = sc.nextLine();
                    System.out.print("Please enter the Football Home team's score: ");
                    int hTFScore = sc.nextInt();
                    System.out.print("Please enter the Football Away team's score: ");
                    int aTFScore = sc.nextInt();
                    sc.nextLine();

                    footballLeague1.recordMatch(homeTFN, awayTFN, hTFScore, aTFScore);
                }

                case 5 -> {
                    System.out.print("Please enter the Volleyball Home team's name: ");
                    String homeTVN = sc.nextLine();
                    System.out.print("Please enter the Volleyball Away team's name: ");
                    String awayTVN = sc.nextLine();
                    System.out.print("Please enter the Volleyball Home team's sets: ");
                    int hTVSets = sc.nextInt();
                    System.out.print("Please enter the Volleyball Away team's sets: ");
                    int aTVSets = sc.nextInt();
                    sc.nextLine();

                    volleyballLeague1.recordMatch(homeTVN, awayTVN, hTVSets, aTVSets);
                }

                case 6 -> {
                    System.out.print("Please enter the Basketball Home team's name: ");
                    String homeTBN = sc.nextLine();
                    System.out.print("Please enter the Basketball Away team's name: ");
                    String awayTBN = sc.nextLine();
                    System.out.print("Please enter the Basketball Home team's score: ");
                    int hTBScore = sc.nextInt();
                    System.out.print("Please enter the Basketball Away team's score: ");
                    int aTBScore = sc.nextInt();
                    sc.nextLine();

                    basketballLeague1.recordMatch(homeTBN, awayTBN, hTBScore, aTBScore);
                }


                case 7 -> {
                    System.out.println("""
                            \n--------- Remove Club Menu ---------
                            All Types:
                            1. Football League
                            2. Volleyball League
                            3. Basketball League
                            """
                    );
                    System.out.print("Please enter your choice number: ");
                    int leagueChoice = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Please enter the club's name to remove: ");
                    String clubName = sc.nextLine();

                    switch (leagueChoice) {
                        case 1 -> footballLeague1.removeClub(clubName);
                        case 2 -> volleyballLeague1.removeClub(clubName);
                        case 3 -> basketballLeague1.removeClub(clubName);
                        default -> System.out.println("Invalid Menu choice Number. Please try again. (from 1 to 3)");
                    }
                }


                case 8 -> {
                    footballLeague1.sortClubs();
                    volleyballLeague1.sortClubs();
                    basketballLeague1.sortClubs();

                    footballLeague1.displayRankings();
                    volleyballLeague1.displayRankings();
                    basketballLeague1.displayRankings();
                }

                case 9 -> {
                    System.out.println("\nFootball League Match History:");
                    footballLeague1.displayMatchHistory();

                    System.out.println("\nVolleyball League Match History:");
                    volleyballLeague1.displayMatchHistory();

                    System.out.println("\nBasketball League Match History:");
                    basketballLeague1.displayMatchHistory();
                }

                case 10 -> {
                    System.out.println("Top Scoring Club in Football League:");
                    Club fTopSClub = footballLeague1.getTopScoringClub();
                    if (fTopSClub == null) {
                        System.out.println("There are no clubs recorded in Football League.");
                    } else {
                        System.out.println(fTopSClub.getClubName() + " is the top-scoring club in the Football League with "
                                + fTopSClub.getGoalsFor() + " goals.");
                    }
                    System.out.println();

                    System.out.println("Top Scoring Club in Volleyball League:");
                    Club vTopSClub = volleyballLeague1.getTopScoringClub();
                    if (vTopSClub == null) {
                        System.out.println("There are no clubs recorded in Volleyball League.");
                    } else {
                        System.out.println(vTopSClub.getClubName() + " is the top-scoring club in the Volleyball League with "
                                + vTopSClub.getGoalsFor() + " goals/points.");
                    }
                    System.out.println();

                    System.out.println("Top Scoring Club in Basketball League:");
                    Club bTopSClub = basketballLeague1.getTopScoringClub();
                    if (bTopSClub == null) {
                        System.out.println("There are no clubs recorded in Basketball League.");
                    } else {
                        System.out.println(bTopSClub.getClubName() + " is the top-scoring club in the Basketball League with "
                                + bTopSClub.getGoalsFor() + " goals/points.");
                    }
                    System.out.println();
                }

                case 11 -> {
                    int totalPts = 0;

                    for (Club club : footballLeague1.getClubs()) {
                        if (club != null)
                            totalPts += club.getPoints();
                    }

                    for (Club club : volleyballLeague1.getClubs()) {
                        if (club != null)
                            totalPts += club.getPoints();
                    }

                    for (Club club : basketballLeague1.getClubs()) {
                        if (club != null)
                            totalPts += club.getPoints();
                    }

                    System.out.println("Total Points across all leagues: " + totalPts);
                }


                case 12 -> System.out.println("Exiting...");

                default -> System.out.println("Invalid Menu choice Number. Please try again. (from 1 to 12)");
            }

        } while (choice != 12);
        sc.close();
    }
}
