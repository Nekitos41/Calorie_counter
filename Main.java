package sprint_1.calorie_counter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static StepTracker stepTracker = new StepTracker();
    static Converter converter = new Converter();
    static StepTracker.MonthData monthData = new StepTracker.MonthData();
    public static void main(String[] args) {
        int userInput = 0;
        while (userInput != 4) {
            printMenu();
            userInput = inputNumber(1, 4, "Input your choice: ");
            getUserChoice(userInput);
        }
        System.out.println("The program is completed!");
    }

    private static void printMenu() {
        System.out.println("1 - Input the number of steps for the selected day.");
        System.out.println("2 - Print statistics for the selected month.");
        System.out.println("3 - Change your daily steps goal.");
        System.out.println("4 - Exit.");
    }
    private static int inputNumber(int MIN_NUM, int MAX_NUM, String str) {
        int num = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            System.out.print(str);
            try {
                num = Integer.parseInt(scanner.nextLine());
            } catch (Exception q) {
                isIncorrect = true;
                System.out.println("You didn't enter number. Input must consist only digits.");
            }
            if (!isIncorrect & (num < MIN_NUM || num > MAX_NUM)) {
                isIncorrect = true;
                System.out.println("Invalid input range.");
            }
        } while (isIncorrect);
        return num;
    }

    private static void outputInfoAboutStatistics() {
        System.out.println("1 - Output number of steps in each day selected month.");
        System.out.println("2 - Output the sum of steps in selected month.");
        System.out.println("3 - Output max amount of steps selected month");
        System.out.println("4 - Output average amount of steps selected month.");
        System.out.println("5 - Output your distance.");
        System.out.println("6 - Output your burning kilocalories.");
        System.out.println("7 - Output your best series of steps.");
        System.out.println("8 - Return.");
    }
    private static void getStatistics(int month, int num) {
        int sum;
        int max;
        int maxKey;
        float kcal;
        float distance;
        ArrayList<Integer> listOfElem = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> bestSeries = new HashMap<>();
        switch (num) {
            case 1:
                StepTracker.outputStatistics(month, stepTracker.monthData);
                break;
            case 2:
                sum = StepTracker.sumStatistics(month, stepTracker.monthData);
                System.out.println("The sum of steps: "  + sum);
                break;
            case 3:
                max = StepTracker.maxNumberOfSteps(month, stepTracker.monthData);
                System.out.println("The max amount of steps: "  + max);
                break;
            case 4:
                sum = StepTracker.sumStatistics(month, stepTracker.monthData);
                System.out.println("The average amount of steps: " + (sum / 30));
                break;
            case 5:
                sum = StepTracker.sumStatistics(month, stepTracker.monthData);
                distance = converter.getDistance(sum);
                System.out.println("Your distance: " + distance);
                break;
            case 6:
                sum = StepTracker.sumStatistics(month, stepTracker.monthData);
                kcal = converter.getKilocalories(sum);
                System.out.println("Your burning kcal: " + kcal);
                break;
            case 7:
                bestSeries = StepTracker.bestSeries(month, stepTracker.monthData);
                maxKey = StepTracker.getKeyWithMaxValue(month, stepTracker.monthData);
                if(maxKey > 0) {
                    listOfElem = bestSeries.get(maxKey);
                    for (int i = 0; i < listOfElem.size(); i++) {
                        System.out.print(listOfElem.get(i) + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("No days with more steps than your daily goal.");
                }
                break;
            case 8:
                break;
        }
    }

    private static void getUserChoice(int num) {
        int month;
        int day;
        int amountOfSteps;
        int userChoice = 0;
        switch (num) {
            case 1:
                month = inputNumber(1, 12, "Input the number of month: ");
                day = inputNumber(1, 30, "Input the number of day: ");
                amountOfSteps = inputNumber(0, 50000, "Input the number of steps: ");
                monthData.setDay(day - 1, amountOfSteps);
                stepTracker.monthData.put(month, monthData);
                break;
            case 2:
                month = inputNumber(1, 12, "Input the number of month whose statistics you want to receive: ");
                while (userChoice != 8) {
                    outputInfoAboutStatistics();
                    userChoice = inputNumber(1, 8, "Input your choice: ");
                    getStatistics(month, userChoice);
                }
                break;
            case 3:
                StepTracker.changeGoal(inputNumber(1, 50000, "Input your goal for steps per day: "));
                break;
            case 4:
                System.exit(0);
        }
    }

}


