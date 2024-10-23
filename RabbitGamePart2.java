package rabbitgamepart1;

import java.util.Scanner;

public class RabbitGamePart2 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);        
        System.out.println("Enter the size of the pyramid: ");
        int N = scn.nextInt();
        Pyramid(N);
        System.out.println("How many players:(maks " + N + ")");
        int playerNum = scn.nextInt();
        while(playerNum>N){
            System.out.println("Try again");
            playerNum = scn.nextInt();
        }
        int[] playerLoc = new int[2*playerNum];
        for (int i = 0; i < playerNum; i++) {
            //going through the playerlocations to detect any same pairs
            //the index 0 and 1 is player 0's i adn j adn player 1's i and j is index 2 and 3 etc...
            int uniqueI, uniqueJ;
            do {
                uniqueI = RandomI(N);
                uniqueJ = RandomJ(N,uniqueI);
            } while (!isUniquePla(playerLoc, i, uniqueI, uniqueJ));
            playerLoc[2 * i] = uniqueI;
            playerLoc[2 * i + 1] = uniqueJ;
            System.out.println("Player " + i + " - I: " + uniqueI + ", J: " + uniqueJ);
        }
        int[] players = new int[playerNum];
        int[] playerPoints = new int[playerNum];
        int[] playerCarrots = new int[playerNum];
        int[] visitedRab = new int[playerNum];
        System.out.println("How many rats:(maks "+ N +")");
        int ratNum = scn.nextInt();
        while(ratNum>N){
            System.out.println("Try again");
            ratNum = scn.nextInt();
        }
        int[] ratLoc = new int[ratNum*2];
        int[] visitedRat = new int[playerNum];
        for (int i = 0; i < ratNum; i++) {
            //going through the ratlocations to detect any same pairs
            int uniqueI, uniqueJ;
            do {
                uniqueI = RandomI(N);
                uniqueJ = RandomJ(N,uniqueI);
            } while (!isUniqueRat(ratLoc, i, uniqueI, uniqueJ));
            ratLoc[2 * i] = uniqueI;
            ratLoc[2 * i + 1] = uniqueJ;
        }
        System.out.println("How mant rabbits:(maks "+ N +")");
        int rabNum = scn.nextInt();
        while(rabNum>N){
            System.out.println("Try again");
            rabNum = scn.nextInt();
        }
        int[] rabbitLoc = new int[rabNum*2];
        int[] fedRab = new int[playerNum];
        for (int i = 0; i < rabNum; i++) {
            //going through the rabiittlocations to detect any same pairs
            int uniqueI, uniqueJ;
            do {
                uniqueI = RandomI(N);
                uniqueJ = RandomJ(N,uniqueI);
            } while (!isUniqueRab(ratLoc, i, uniqueI, uniqueJ));
            rabbitLoc[2 * i] = uniqueI;
            rabbitLoc[2 * i + 1] = uniqueJ;
        }
       int hevi = RandomI(N);
       int hevj = RandomJ(N,hevi);
       int helli = RandomI(N);
       int hellj = RandomJ(N,helli);
       int goli = RandomI(N);
       int golj = RandomJ(N,goli);
       while(helli == hevi && hevj == hellj || goli == hevi && hevj == hellj || helli == goli && golj == hellj){
           //Making heaven , hell and gollem's locations unique between them 
       helli = RandomI(N);
       hellj = RandomJ(N,helli);
       goli = RandomI(N);
       golj=RandomJ(N,goli);
       }
        while (true) {
            for (int i = 0, j = 1, n = 0; i < playerLoc.length && j < playerLoc.length && n < playerNum; i += 2, j += 2, n++) {
                System.out.println("Player " + n + " 's turn ----- Points: " + playerPoints[n] + " ----- Carrots: " + playerCarrots[n] + " ----- Rabbits visited since the begining: " + visitedRab[n]);
                System.out.println("Which direction 8: up, 2: down, 6: right, 4: left");
                System.out.println("You are at " + playerLoc[i] + " , " + playerLoc[j]);
                int ways = scn.nextInt();
                switch (ways) {
                    case 8:
                        if (canMove(playerLoc[i], playerLoc[j], 8, N)) {
                            playerLoc[i]--;
                            playerLoc[j]--;
                            System.out.println("You went up  to " + (playerLoc[i]) + "," + (playerLoc[j]));
                            if (isThereCarrot(playerLoc[i], playerLoc[j])) {
                                System.out.println("There is a carrot");
                                System.out.println("Which kind of question you want 1: addition," + "2: subtraction, 3: division, 4: multiplication");
                                int choice = scn.nextInt();
                                if (randomQ(choice)) {
                                    System.out.println("You collected a carrot");
                                    playerCarrots[n]++;
                                } else{
                                System.out.println("Incorrect");
                            }
                            } else if (isThereRabbit(rabbitLoc, playerLoc[i], playerLoc[j])) {
                                visitedRab[n]++;
                                System.out.println("There is a rabbit here...");
                                if (reqCarrot() == playerCarrots[n]) {
                                    fedRab[n]++;
                                    playerPoints[n] = playerPoints[n] + 100;
                                }
                                playerPoints[n] = playerPoints[n] - 2;

                            } else if (isThereRat(ratLoc, playerLoc[i], playerLoc[j])) {
                                if (playerCarrots[n] != 0) {
                                    System.out.println("THERE WAS A RAT HERE IT TOOK ONE OF YOUR CARROTS AND SOME POINTS!!!!");
                                    playerCarrots[n]--;
                                    visitedRat[n]++;
                                    playerPoints[n] = playerPoints[n] - 50;
                                }
                            } else if (isThereGolem(goli, golj, playerLoc[i], playerLoc[j])) {
                                System.out.println("There is a gollem here your points gone to 100");
                                playerPoints[n] = 100;
                            }
                        } else {
                            System.out.println("You can't go up");
                        }
                        break;
                    case 2:
                        if (canMove(playerLoc[i], playerLoc[j], 2, N)) {
                            playerLoc[i]++;
                            playerLoc[j]++;
                            System.out.println("You went down   to " + (playerLoc[i]) + "," + (playerLoc[j]));
                            if (isThereCarrot(playerLoc[i], playerLoc[j])) {
                                System.out.println("There is a carrot");
                                System.out.println("Which kind of question you want 1: addition," + "2: subtraction, 3: division, 4: multiplication");
                                int choice = scn.nextInt();
                                if (randomQ(choice)) {
                                    System.out.println("You collected a carrot");
                                    playerCarrots[n]++;
                                } else{
                                System.out.println("Incorrect");
                            }
                            } else if (isThereRabbit(rabbitLoc, playerLoc[i], playerLoc[j])) {
                                visitedRab[n]++;
                                System.out.println("There is a rabbit here...");
                                if (reqCarrot() == playerCarrots[n]) {
                                    fedRab[n]++;
                                    playerPoints[n] = playerPoints[n] + 100;
                                }
                                playerPoints[n] = playerPoints[n] - 2;

                            } else if (isThereRat(ratLoc, playerLoc[i], playerLoc[j])) {
                                if (playerCarrots[n] != 0) {
                                    System.out.println("THERE WAS A RAT HERE IT TOOK ONE OF YOUR CARROTS AND SOME POINTS!!!!");
                                    playerCarrots[n]--;
                                    visitedRat[n]++;
                                    playerPoints[n] = playerPoints[n] - 50;
                                }
                            } else if (isThereGolem(goli, golj, playerLoc[i], playerLoc[j])) {
                                System.out.println("There is a gollem here your points gone to 100");
                                playerPoints[n] = 100;
                            }
                        } else {
                            System.out.println("You can't go down");
                        }
                        break;
                    case 6:
                        if (canMove(playerLoc[i], playerLoc[j], 6, N)) {
                            playerLoc[j]++;
                            System.out.println("You went right  to " + (playerLoc[i]) + "," + (playerLoc[j]));
                            if (isThereCarrot(playerLoc[i], playerLoc[j])) {
                                System.out.println("There is a carrot");
                                System.out.println("Which kind of question you want 1: addition," + "2: subtraction, 3: division, 4: multiplication");
                                int choice = scn.nextInt();
                                if (randomQ(choice)) {
                                    System.out.println("You collected a carrot");
                                    playerCarrots[n]++;
                                } else{
                                System.out.println("Incorrect");
                            }
                            } else if (isThereRabbit(rabbitLoc, playerLoc[i], playerLoc[j])) {
                                visitedRab[n]++;
                                System.out.println("There is a rabbit here...");
                                if (reqCarrot() == playerCarrots[n]) {
                                    fedRab[n]++;
                                    playerPoints[n] = playerPoints[n] + 100;
                                }
                                playerPoints[n] = playerPoints[n] - 2;

                            } else if (isThereRat(ratLoc, playerLoc[i], playerLoc[j])) {
                                if (playerCarrots[n] != 0) {
                                    System.out.println("THERE WAS A RAT HERE IT TOOK ONE OF YOUR CARROTS AND SOME POINTS!!!!");
                                    playerCarrots[n]--;
                                    visitedRat[n]++;
                                    playerPoints[n] = playerPoints[n] - 50;
                                }
                            } else if (isThereGolem(goli, golj, playerLoc[i], playerLoc[j])) {
                                System.out.println("There is a gollem here your points gone to 100");
                                playerPoints[n] = 100;
                            }
                        } else {
                            System.out.println("You can't go right");
                        }
                        break;
                    case 4:
                        if (canMove(playerLoc[i], playerLoc[j], 4, N)) {
                            playerLoc[j]--;
                            System.out.println("You went left to " + (playerLoc[i]) + "," + (playerLoc[j]));
                            if (isThereCarrot(playerLoc[i], playerLoc[j])) {
                                System.out.println("There is a carrot");
                                System.out.println("Which kind of question you want 1: addition," + "2: subtraction, 3: division, 4: multiplication");
                                int choice = scn.nextInt();
                                if (randomQ(choice)) {
                                    System.out.println("You collected a carrot");
                                    playerCarrots[n]++;
                                } else{
                                System.out.println("Incorrect");
                            }
                            } else if (isThereRabbit(rabbitLoc, playerLoc[i], playerLoc[j])) {
                                visitedRab[n]++;
                                System.out.println("There is a rabbit here...");
                                if (reqCarrot() == playerCarrots[n]) {
                                    fedRab[n]++;
                                    playerPoints[n] = playerPoints[n] + 100;
                                }
                                playerPoints[n] = playerPoints[n] - 2;

                            } else if (isThereRat(ratLoc, playerLoc[i], playerLoc[j])) {
                                if (playerCarrots[n] != 0) {
                                    System.out.println("THERE WAS A RAT HERE IT TOOK ONE OF YOUR CARROTS AND SOME POINTS!!!!");
                                    playerCarrots[n]--;
                                    visitedRat[n]++;
                                    playerPoints[n] = playerPoints[n] - 50;
                                }
                            } else if (isThereGolem(goli, golj, playerLoc[i], playerLoc[j])) {
                                System.out.println("There is a gollem here your points gone to 100");
                                playerPoints[n] = 100;
                            }
                        } else {
                            System.out.println("You can't go left");
                        }
                        break;
                    default:
                        System.out.println("Invalid input...");
                }
                if (playerLoc[i] == hevi && playerLoc[j] == hevj && findWinner(hevi, hevj, playerLoc, players, playerPoints) == n) {
                    System.out.println("THE WINNER IS PLAYER " + n);
                    System.out.println("Points: " + playerPoints[n] + " ----- Carrots: " + playerCarrots[n] + " ----- Rabbits visited since the begining: " + visitedRab[n]);
                    System.out.println("THE MOST CARROT COLLECTED PLAYER IS " + findCarrotCollector(playerCarrots));
                    System.out.println("THE MOST RABBIT FED PLAYER IS " + findRabbitFeeder(fedRab));
                    System.out.println("THE MOST RAT VISITING PLAYER IS " + findRatVisitor(visitedRat));
                    finalPyramid(N, playerLoc,rabbitLoc,ratLoc , hevi, hevj, helli, hellj, goli, golj);
                    System.exit(0);
                }if(playerLoc[i] == helli && playerLoc[j] == hellj){
                    System.out.println("THE LOSER IS PLAYER " + n);
                    System.out.println("Points: " + playerPoints[n] + " ----- Carrots: " + playerCarrots[n] + " ----- Rabbits visited since the begining: " + visitedRab[n]);
                    System.out.println("THE MOST CARROT COLLECTED PLAYER IS " + findCarrotCollector(playerCarrots));
                    System.out.println("THE MOST RABBIT FED PLAYER IS " + findRabbitFeeder(fedRab));
                    System.out.println("THE MOST RAT VISITING PLAYER IS " + findRatVisitor(visitedRat));
                    System.out.println("If you want to see the locations of the entities ente  -1 or if you directly exit enter 1");
                    finalPyramid(N, playerLoc,rabbitLoc,ratLoc , hevi, hevj, helli, hellj, goli, golj);
                    System.exit(0);
                    
                }

            }
            
        }
        
 }

    public static void Pyramid(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N - i; j++) {
                System.out.print("       ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("| " + i + "," + j + " |");
            }
            System.out.println();
        }
    }
    public static int RandomI(int N) {
        int i = 1 + (int) (Math.random() * N);
        return i;
    }
    public static int RandomJ(int N, int i) {
        int maxJ = 2 * N - 1;
        int j;

        do {
            j = 1 + (int) (Math.random() * maxJ);
        } while (j > 2 * i - 1);

        return j;
    }
    public static boolean isUniquePla(int[] playerLoc, int playerIndex, int uniqueI, int uniqueJ) {
        //Checking players locations for uniqueness
        for (int i = 0; i < playerIndex; i++) {
            int storedI = playerLoc[2 * i];
            int storedJ = playerLoc[2 * i + 1];

            
            if (storedI == uniqueI && storedJ == uniqueJ) {
                return false;
            }
        }
        return true;
    }
    public static boolean isUniqueRat(int[] ratLoc, int ratIndex, int uniqueI, int uniqueJ) {
        //Checking rat locations for uniqueness
        for (int i = 0; i < ratIndex; i++) {
            int storedI = ratLoc[2 * i];
            int storedJ = ratLoc[2 * i + 1];

            
            if (storedI == uniqueI && storedJ == uniqueJ) {
                return false;
            }
        }
        return true;
    }
    public static boolean isUniqueRab(int[] rabbitLoc, int rabIndex, int uniqueI, int uniqueJ) {
        //Checking  rabbit locations for uniqueness
        for (int i = 0; i < rabIndex; i++) {
            int storedI = rabbitLoc[2 * i];
            int storedJ = rabbitLoc[2 * i + 1];

            
            if (storedI == uniqueI && storedJ == uniqueJ) {
                return false;
            }
        }
        return true;
    }
     public static boolean randomQ(int type) {
        Scanner scn = new Scanner(System.in);
        switch (type) {
            case 1:
                //Generating a number addition question 
                int fr = 1 + (int) (Math.random() * 10);
                int sc = 1 + (int) (Math.random() * 10);
                int res = fr + sc;
                System.out.println("--What is " + fr + " + " + sc + " =?");
                int ans = scn.nextInt();
                if(res==ans){
                    return true;
                }
            case 2:
                //Generating a number substution question 
                int fr2 = 1 + (int) (Math.random() * 10);
                int sc2 = 1 + (int) (Math.random() * 10);
                int res2 = fr2 - sc2;
                System.out.println("--What is " + fr2 + " - " + sc2 + " =?");
                int ans2 = scn.nextInt();
                if(res2==ans2){
                    return true;
                }
            case 3:
                //Generating a number division question 
                int fr3 = 1 + (int) (Math.random() * 10);
                int sc3 = 1 + (int) (Math.random() * 10);
                int res3 = fr3 / sc3;
                System.out.println("--What is " + fr3 + " / " + sc3 + " =?(int values)");
                int ans3 = scn.nextInt();
                if(res3==ans3){
                    return true;
                }
            case 4:
                //Generating a number multiplication question 
                int fr4 = 1 + (int) (Math.random() * 10);
                int sc4 = 1 + (int) (Math.random() * 10);
                int res4 = fr4 * sc4;
                System.out.println("--What is " + fr4 + " * " + sc4 + " =?");
                int ans4 = scn.nextInt();
                if(res4==ans4){
                    return true;
                }
            default:
                return false;
        }
    }
    public static boolean isThereCarrot(int i, int j) {
        if (i % 2 == 0 && j % 2 == 0) {
            return true;
        }
        return false;
    }
    public static boolean isThereRabbit(int[] rabbitLoc, int playeri, int playerj) {
        for (int i = 0, j = 1; i <= rabbitLoc.length && j < rabbitLoc.length; i += 2, j += 2) {
        //Going through the rabbit locations to find a match
            if (rabbitLoc[i] == playeri && rabbitLoc[i] == playerj) {
                return true;
            }
        }
        return false;
    }
    public static boolean isThereRat(int[] ratLoc, int playeri, int playerj) {
        for (int i = 0, j = 1; i <= ratLoc.length && j < ratLoc.length; i += 2, j += 2) {
        //Going through the rat locations to find a match
            if (ratLoc[i] == playeri && ratLoc[i] == playerj) {
                return true;
            }
        }
        return false;
    }
    public static boolean isThereGolem(int i1, int j1, int playeri, int playerj) {
        if (i1 == playeri && j1 == playerj) {
            return true;
        }
        return false;
    }
    public static boolean exitToHeaven(int hevi, int hevj, int playeri, int playerj) {
        if (hevi == playeri && hevj == playerj) {
            return true;
        }
        return false;
    }
    public static boolean exitToHell(int helli, int hellj, int playeri, int playerj) {
        if (helli == playeri && hellj == playerj) {
            return true;
        }
        return false;
    }
    public static int reqCarrot() {
        int reqCarrot = 1 + (int) Math.random() * 3;
        return reqCarrot;
    }
    public static boolean canMove(int i, int j, int direction, int N) {
        switch (direction) {
        case 8:
            if ((i - 1) >= 1 && j >= 2 && j <= 2 * (i - 1)) {
                return true;
            }
            break;
        case 2:
            if ((i + 1) <= N) {
                return true;
            }
            break;
        case 6:
            if ((j + 1) <= 2 * i - 1) {
                return true;
            }
            break;
        case 4:
            if ((j - 1) >= 1) {
                return true;
            }
            break;
        default:
            return false;
    }
    return false;
    }
    public static void finalPyramid(int N, int[] playerLoc, int[] rabbitLoc, int[] ratLoc,
        int hevi, int hevj, int helli, int hellj, int goli, int golj) {
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N - i; j++) {
            System.out.print("         ");
        }
        for (int j = 1; j <= 2 * i - 1; j++) {
            int currentPlayer = -1;
            for (int k = 0; k < playerLoc.length; k += 2) {
                //going through the player locations to see a match
                if (i == playerLoc[k] && j == playerLoc[k + 1]) {
                    currentPlayer = k / 2;
                    break;
                }
            }
            if (currentPlayer != -1) {
                System.out.print("| Player " + currentPlayer + " |");
                continue;
            }

            for (int k = 0; k < rabbitLoc.length; k += 2) {
                if (i == rabbitLoc[k] && j == rabbitLoc[k + 1]) {
                    //going through the rabbit locations to see a match
                    System.out.print("| Rab |");
                    continue;
                }
            }

            for (int k = 0; k < ratLoc.length; k += 2) {
                if (i == ratLoc[k] && j == ratLoc[k + 1]) {
                    //going through the rat locations to see a match
                    System.out.print("| Rat |");
                    continue;
                }
            }

            if (i == hevi && j == hevj) {
                System.out.print("| Heaven |");
                continue;
            } else if (i == helli && j == hellj) {
                System.out.print("| Hell |");
                continue;
            } else if (i % 2 == 0 && j % 2 == 0) {
                System.out.print("| Carrot |");
                continue;
            } else if (i == goli && j == golj) {
                System.out.print("| Golem |");
                continue;
            }
            System.out.print("| " + i + "," + j + " |");
        }
        System.out.println();
    }
}
    public static int findWinner(int hevi, int hevj, int[] playerLoc, int[] players, int[] playerPoints) {
        //The winner will be determined by going to heaven and having most points
        for (int i = 0, j = 1, n = 0; i < playerLoc.length && j <= playerLoc.length; i += 2, j += 2, n++) {
            if ((hevi == playerLoc[i] && hevj == playerLoc[j]) && maxPoints(playerPoints, n)) {
                return n;
            }
        }
        return -1;
    }
    public static boolean maxPoints(int[] playerPoints, int n) {
        int maxpo = playerPoints[n];
        //Going through the player point to detemine the highest point player
        for (int i = 0; i < playerPoints.length; i++) {
            if (playerPoints[i] > maxpo) {
                return false;
            }
        }
        return true;
    }
    public static int findCarrotCollector(int[] playerCarrots) {
        //Going through the player carrots to detemine the highest carrot collector
        int maxcar = playerCarrots[0];
        int maxCarrotColIndex = 0;
        for (int i = 0, n = 0; i < playerCarrots.length; i++, n++) {
            if (playerCarrots[i] > maxcar) {
                maxcar = playerCarrots[i];
                maxCarrotColIndex = n;
            }
        }
        return maxCarrotColIndex;
    }
    public static int findRabbitFeeder(int[] fedRab) {
        //Going through the player rabbit fed count  to detemine the most rabbit fed player
        int maxrab = fedRab[0];
        int maxIndex = 0;
        for (int i = 0, n = 0; i < fedRab.length; i++, n++) {
            if (fedRab[i] > maxrab) {
                maxrab = fedRab[i];
                maxIndex = n;
            }
        }
        return maxIndex;
    }
    public static int findRatVisitor(int[] visitedRat) {
        //Going through the player rat count to detemine the most rat visiting player
        int maxrat = visitedRat[0];
        int maxIndex = 0;
        for (int i = 0, n = 0; i < visitedRat.length; i++, n++) {
            if (visitedRat[i] > maxrat) {
                maxrat = visitedRat[i];
                maxIndex = n;
            }
        }
        return maxIndex;
    }

}
