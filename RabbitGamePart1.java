package rabbitgamepart1;

import java.util.*;
public class RabbitGamePart1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the size of the pyramid: ");
        int carrot = 0;
        int gamePoints = 0;
        int rabbitCount = 0;
        int N = scn.nextInt();
        Pyramid(N);
        //Creating random pairs for entities of the game
        int hevi = RandomI(N);
        int hevj = RandomJ(N, hevi);
        int helli = RandomI(N);
        int hellj = RandomJ(N, helli);
        int playeri = RandomI(N);
        int playerj = RandomJ(N, playeri);
        int i1 = RandomI(N);
        int j1 = RandomJ(N, i1);
        int i2 = RandomI(N);
        int j2 = RandomJ(N, i2);
        int i3 = RandomI(N);
        int j3 = RandomJ(N, i3);
        int i4 = RandomI(N);
        int j4 = RandomJ(N, i4);
        int i5 = RandomI(N);
        int j5 = RandomJ(N, i5);
        //Added a golem character which when player gets to the golem's location their points will go to 100 even if their points was above 100.
        int goli = RandomI(N);
        int golj = RandomJ(N, goli);
        //Checking the uniqueness of the pairs
        while (!isPairUnique(i2, j2, i1, j1) || !isPairUnique(i3, j3, i1, j1) || !isPairUnique(i4, j4, i1, j1)
                || !isPairUnique(i5, j5, i1, j1) || !isPairUnique(i3, j3, i2, j2) || !isPairUnique(i4, j4, i2, j2)
                || !isPairUnique(i5, j5, i2, j2) || !isPairUnique(i4, j4, i3, j3) || !isPairUnique(i5, j5, i3, j3)
                || !isPairUnique(i5, j5, i4, j4) || !isPairUnique(helli, hellj, hevi, hevj) || !isPairUnique(i1, j1, hevi, hevj)
                || !isPairUnique(i2, j2, hevi, hevj) || !isPairUnique(i3, j3, hevi, hevj) || !isPairUnique(i4, j4, hevi, hevj)
                || !isPairUnique(i5, j5, hevi, hevj) || !isPairUnique(i1, j1, helli, hellj)
                || !isPairUnique(i2, j2, helli, hellj) || !isPairUnique(i3, j3, helli, hellj) || !isPairUnique(i4, j4, helli, hellj)
                || !isPairUnique(i5, j5, helli, hellj) || !isPairUnique(goli, golj, i1, j1) || !isPairUnique(goli, golj, i2, j2)
                || !isPairUnique(goli, golj, i3, j3) || !isPairUnique(goli, golj, i4, j4) || !isPairUnique(goli, golj, i5, j5)
                || !isPairUnique(goli, golj, helli, hellj) || !isPairUnique(goli, golj, hevi, hevj)) {
            i2 = RandomI(N);
            j2 = RandomJ(N, i2);
            i3 = RandomI(N);
            j3 = RandomJ(N, i3);
            i4 = RandomI(N);
            j4 = RandomJ(N, i4);
            i5 = RandomI(N);
            j5 = RandomJ(N, i5);
            helli = RandomI(N);
            hellj = RandomJ(N, helli);
            hevi = RandomI(N);
            hevj = RandomJ(N, hevi);
            goli = RandomI(N);
            golj = RandomJ(N, goli);
        }
        System.out.println("Points: " + gamePoints + " ----- Carrots: " + carrot + " ----- Rabbits visited since the begining: " + rabbitCount);
        System.out.println("Which direction 8: up, 2: down, 6: right, 4: left");
        System.out.println("You are at " + playeri + " , " + playerj);
        while (!exitToHeaven(hevi, hevj, playeri, playerj) && !exitToHell(hevi, hevj, playeri, playerj)) {
            //Continuing while the condition is not met
            int ways = scn.nextInt();
            switch (ways) {
                case 8:
                    if (canMove(playeri, playerj, 8, N)) /*Checking if the player can move in the wanted direction */{
                        System.out.println("You went up  to " + (playeri - 1) + "," + (playerj - 1));
                        playeri--;/*Moving the player to the new location */
                        playerj--;
                        if (isThereCarrot(playeri, playerj)) {
                            System.out.println("There is a carrot");
                            System.out.println("Which kind of question you want 1: addition," + "2: subtraction, 3: division, 4: multiplication");
                            int choice = scn.nextInt();
                            if (randomQ(choice)) {
                                System.out.println("You collected a carrot");
                                carrot++;
                            } else{
                                System.out.println("Incorrect");
                            }
                        } else if (isThereRabbit(i1, j1, i2, j2, i3, j3, playeri, playerj))/*Checking if players location met any rabbits location*/ {
                            rabbitCount++;
                            System.out.println("There is a rabbit here...");
                            if (reqCarrot() == carrot) {
                                carrot++;
                                gamePoints = gamePoints + 100;
                            }
                            gamePoints = gamePoints - 2;

                        } else if (isThereRat(i4, j4, i5, j5, playeri, playerj))/*Checking if players location met any rats location*/ {
                            if (carrot != 0) {
                                System.out.println("THERE WAS A RAT HERE IT TOOK ONE OF YOUR CARROTS AND SOME POINTS!!!!");
                                carrot--;
                                gamePoints = gamePoints - 50;
                            }
                        } else if (isThereGolem(goli, golj, playeri, playerj))/*Checking if players location met any gollems location*/ {
                            System.out.println("There is a golem here your points gone to 100");
                            gamePoints = 100;
                        }
                    } else {
                        System.out.println("You can't go up");
                    }
                    break;
                case 2:
                    if (canMove(playeri, playerj, 2, N)) /*Checking if the player can move in the wanted direction */{
                        System.out.println("You went down  to " + (playeri + 1) + "," + (playerj + 1));
                        playeri++;/*Moving the player to the new location */
                        playerj++;
                        if (isThereCarrot(playeri, playerj)) {
                            System.out.println("There is a carrot");
                            System.out.println("Which kind of question you want 1: addition," + "2: subtraction, 3: division, 4: multiplication");
                            int choice = scn.nextInt();
                            if (randomQ(choice)) {
                                System.out.println("You collected a carrot");
                                carrot++;
                            } else{
                                System.out.println("Incorrect");
                            }                        
                        } else if (isThereRabbit(i1, j1, i2, j2, i3, j3, playeri, playerj)) /*Checking if players location met any rabbits location*/{
                            rabbitCount++;
                            System.out.println("There is a rabbit here...");
                            if (reqCarrot() == carrot) {
                                carrot++;
                                gamePoints = gamePoints + 100;
                            }
                            gamePoints = gamePoints - 2;

                        } else if (isThereRat(i4, j4, i5, j5, playeri, playerj))/*Checking if players location met any rats location*/ {
                            if (carrot != 0) {
                                System.out.println("THERE WAS A RAT HERE IT TOOK ONE OF YOUR CARROTS AND SOME POINTS!!!!");
                                carrot--;
                                gamePoints = gamePoints - 50;
                            }
                        } else if (isThereGolem(goli, golj, playeri, playerj))/*Checking if players location met any gollems locaiton*/ {
                            System.out.println("There is a golem here your points gone to 100");
                            gamePoints = 100;
                        }
                    } else {
                        System.out.println("You can't go down");
                    }
                    break;
                case 6:
                    if (canMove(playeri, playerj, 6, N)) /*Checking if the player can move in the wanted direction */{
                        System.out.println("You went right to " + (playeri) + "," + (playerj + 1));
                        playerj++;/*Moving the player to the new location */
                        if (isThereCarrot(playeri, playerj)) {
                            System.out.println("There is a carrot");
                            System.out.println("Which kind of question you want 1: addition," + "2: subtraction, 3: division, 4: multiplication");
                            int choice = scn.nextInt();
                            if (randomQ(choice)) {
                                System.out.println("You collected a carrot");
                                carrot++;
                            } else{
                                System.out.println("Incorrect");
                            }
                        } else if (isThereRabbit(i1, j1, i2, j2, i3, j3, playeri, playerj)) /*Checking if players location met any rabbits location*/{
                            rabbitCount++;
                            System.out.println("There is a rabbit here...");
                            if (reqCarrot() == carrot) {
                                carrot++;
                                gamePoints = gamePoints + 100;
                            }
                            gamePoints = gamePoints - 2;

                        } else if (!isThereRat(i4, j4, i5, j5, playeri, playerj))/*Checking if players location met any rats location*/ {
                            if (carrot != 0) {
                                System.out.println("THERE WAS A RAT HERE IT TOOK ONE OF YOUR CARROTS AND SOME POINTS!!!!");
                                carrot--;
                                gamePoints = gamePoints - 50;
                            }
                        } else if (isThereGolem(goli, golj, playeri, playerj))/*Checking if players location met any gollems locaiton*/  {
                            System.out.println("There is a golem here your points gone to 100");
                            gamePoints = 100;
                        }
                    } else {
                        System.out.println("You can't go right");
                    }
                    break;
                case 4:
                    if (canMove(playeri, playerj, 4, N))/*Checking if the player can move in the wanted direction */ {
                        System.out.println("You went left  to " + (playeri) + "," + (playerj - 1));
                        playerj--;/*Moving the player to the new location */
                        if (isThereCarrot(playeri, playerj)) {
                            System.out.println("There is a carrot");
                            System.out.println("Which kind of question you want 1: addition," + "2: subtraction, 3: division, 4: multiplication");
                            int choice = scn.nextInt();
                            if (randomQ(choice)) {
                                System.out.println("You collected a carrot");
                                carrot++;
                            } else{
                                System.out.println("Incorrect");
                            }
                        } else if (isThereRabbit(i1, j1, i2, j2, i3, j3, playeri, playerj)) /*Checking if players location met any rabbits location*/{
                            rabbitCount++;
                            System.out.println("There is a rabbit here...");
                            if (reqCarrot() == carrot) {
                                carrot++;
                                gamePoints = gamePoints + 100;
                            }
                            gamePoints = gamePoints - 2;
                        } else if (isThereRat(i4, j4, i5, j5, playeri, playerj))/*Checking if players location met any rats location*/ {
                            if (carrot != 0) {
                                System.out.println("THERE WAS A RAT HERE IT TOOK ONE OF YOUR CARROTS AND SOME POINTS!!!!");
                                carrot--;
                                gamePoints = gamePoints - 50;
                            }
                        } else if (isThereGolem(goli, golj, playeri, playerj))/*Checking if players location met any gollems locaiton*/  {
                            System.out.println("There is a golem here your points gone to 100");
                            gamePoints = 100;
                        }
                    } else {
                        System.out.println("You can't go left");
                    }
                    break;
                    default:
                        System.out.println("Invalid input....");
            }

            if (exitToHell(helli, hellj, playeri, playerj))/*Printing infroming string when players met losing conditions*/ {
                System.out.println("----YOU'VE REACHED HELL YOU LOSE----");
                System.out.println("Points: " + gamePoints + " ----- Carrots: " + 0 + " ----- Rabbits visited since the begining: " + rabbitCount);
                break;
            }
            if (exitToHeaven(hevi, hevj, playeri, playerj))/*Printing infroming string when players met winning conditions*/ {
                System.out.println("----YOU'VE REACHED HEAVEN YOU WIN----");
                System.out.println("Points: " + gamePoints + " ----- Carrots: " + carrot + " ----- Rabbits visited since the begining: " + rabbitCount);
                break;
            }
            System.out.println("You are at " + playeri + " , " + playerj);
            System.out.println("Points: " + gamePoints + " ----- Carrots: " + carrot + " ----- Rabbits visited since the begining: " + rabbitCount);
            System.out.println("Which direction 8: up, 2: down, 6: right, 4: left");
        }
        finalPyramid(N, playeri, playerj, i1, j1, i2, j2, i3, j3, i4, j4, i5, j5, hevi, hevj, helli, hellj, goli, golj);
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
    public static boolean isPairUnique(int i, int j, int prevI, int prevJ) {
        return i != prevI || j != prevJ;
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
                break;
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
                break;
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
                break;
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
        return false;
    }
    public static boolean isThereCarrot(int i, int j) {
        if (i % 2 == 0 && j % 2 == 0) {
            return true;
        }
        return false;
    }
    public static boolean isThereRabbit(int i1, int j1, int i2, int j2, int i3, int j3, int playeri, int playerj) {
        return (i1 == playeri && j1 == playerj) || (i2 == playeri && j2 == playerj) || (i3 == playeri && j3 == playerj);
    }
    public static boolean isThereRat(int i1, int j1, int i2, int j2, int playeri, int playerj) {
        if ((i1 == playeri && j1 == playerj) || (i2 == playeri && j2 == playerj)) {
            return true;
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
        return (playeri == hevi && playerj == hevj);
    }

    public static boolean exitToHell(int helli, int hellj, int playeri, int playerj) {
        return (playeri == helli && playerj == hellj);
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
    public static void finalPyramid(int N, int playeri, int playerj, int i1, int j1, int i2, int j2, int i3, int j3, int i4, int j4,
            int i5, int j5, int hevi, int hevj, int helli, int hellj, int goli, int golj) {
        //Goint through the pyramid with the entities location if one match we change the location numbers to informing strings
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N - i; j++) {
                System.out.print("         ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                if (i == playeri && j == playerj) {
                    System.out.print("| Last Location of Player |");
                    continue;
                } else if (i == i1 && j == j1 || i == i2 && j == j2 || i == i3 && j == j3) {
                    System.out.print("| Rab |");
                    continue;
                } else if (i == i4 && j == j4 || i == i5 && j == j5) {
                    System.out.print("| Rat |");
                    continue;
                } else if (i == hevi && j == hevj) {
                    System.out.print("| Heaven |");
                    continue;
                } else if (i == helli && j == hellj) {
                    System.out.print("| Hell |");
                    continue;
                } else if (i % 2 == 0 && j % 2 == 0) {
                    System.out.print("| Carrot |");
                    continue;
                } else if (i == goli && j == golj) {
                    System.out.println("| Golem |");
                    continue;
                }
                System.out.print("| " + i + "," + j + " |");
            }
            System.out.println();
        }
    }
}
