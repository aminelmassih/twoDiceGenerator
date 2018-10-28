package twodiceassignment;

import java.util.Scanner;
import jdk.nashorn.internal.ir.ContinueNode;

/**
 * To implement a dice game.
 * a. The use have two dices, every time the user will roll the dice and check the sum.
 * i. If (sum == 7 || sum ==11) user win
 * ii. If (sum == 2 || sum == 3 || sum == 12) user lost
 * iii. If (other case) the sum will be saved, and the user has to roll the dice again and
 * again, if the user re-roll the dice to the saved number, the user win, if before that
 * the user get a 7, the user lost.
 * 
 * @author ameenalmassih
 * @since 5/2/2018
 */
public class TwoDiceAssignment {

    static Scanner console = new Scanner(System.in);
    
    public static void main(String[] args) {
        //declare variables
        int dice1;
        int dice2;
        int sum;
        int sum2;
        boolean win = false;
        double tolerance = 0.0;
        int totalTimes = 10000;
        int winTimes = 0;
        double winProb;
        double loseProb;
        
        for (int i = 0; i < totalTimes; i++){       
            //give a randome number to dice1 & dice2
            dice1 = generateDice();
            dice2 = generateDice();

            //sum calculation
            sum = dice1 + dice2;

            //checking if win or lose 
            switch (sum){
                case 7:
                case 11:
                    printResult(true);
                    winTimes++;
                    System.out.printf("%-5d%n",sum);
                    break;
                case 2:
                case 3:
                case 12:
                    printResult(true);
                    System.out.printf("%-5d%n",sum);
                    break;
               default:
                    while (true){
                        dice1 = generateDice();
                        dice2 = generateDice();
                        sum2 = dice1 + dice2;
                            if (sum2 == sum){
                                System.out.printf("%-5d%n",sum2);
                                printResult(win);
                                winTimes++;
                                break;
                            }
                            else if (sum2 == 7){
                                System.out.printf("%-5d%n",sum2);
                                printResult(win);
                                break;
                            }
                    }
            }
        }
        winProb = 1.0 * winTimes / totalTimes;
        loseProb = 1 - winProb;
        System.out.printf("Win: %.2f%%%n", winProb * 100);
        System.out.printf("Lose: %.2f%%%n", loseProb * 100);
        System.out.println((isFairGame(winProb, loseProb, tolerance))); 
    }
    
    /**
     * Method to generate rolling dice
     * @return: the generated random number
     */
    public static int generateDice(){
        return (int)(Math.random() * 6) + 1;
    }
    
    /**
     * Method to print if the result Win or Lose
     * @param win 
     */
    public static void printResult(boolean win){
        if (win)
            System.out.printf("%-5s%n", "Win");
        else
            System.out.printf("%-5s%n", "Lose");
    }
    
    /**
     * Method to presume if this game is fair or not
     * @param winProb the winning percentage
     * @param lossProb the loosing percentage
     * @param tolerance the tolerance that i bear
     * @return it will return the result if this game is fair or not based on the tolerance
     */
    public static boolean isFairGame(double winProb, double lossProb, double tolerance){
        return Math.abs (winProb - lossProb) <= tolerance;
    }
    
}
