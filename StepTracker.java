package sprint_1.calorie_counter;

import java.util.ArrayList;
import java.util.HashMap;


public class StepTracker {

    HashMap<Integer, MonthData> monthData = new HashMap<>();

    public static int goal = 10000;

    public StepTracker() {
        for(int i = 0; i < 12; i++) {
            monthData.put(i, new MonthData());
        }
    }

    public static class MonthData {
        HashMap<Integer, Integer> dayData = new HashMap<>();

        public void setDay(int day, int amountOfSteps) {
            dayData.put(day, amountOfSteps);
        }
    }

    public static int sumStatistics(int month, HashMap<Integer, MonthData> monthData) {
        int sum = 0;
        for (int i = 0; i < 30; i++) {
            if(monthData.get(month).dayData.get(i) != null)
                sum += monthData.get(month).dayData.get(i);
            else
                sum += 0;
        }
        return sum;
    }

    public static void outputStatistics(int month, HashMap<Integer, MonthData> monthData) {
        for (int i = 0; i < 30; i++) {
            if(monthData.get(month).dayData.get(i) != null && (i != 29))
                System.out.print((i + 1) + " day: " + monthData.get(month).dayData.get(i) + ", ");
            else if(monthData.get(month).dayData.get(i) != null && (i == 29))
                System.out.println((i + 1) + " day: " + monthData.get(month).dayData.get(i) + ".");
            else if(monthData.get(month).dayData.get(i) == null && (i == 29))
                System.out.println((i + 1) + " day: 0.");
            else
                System.out.print((i + 1) + " day: 0, ");
            if(i == 9 || i == 19)
                System.out.println();
        }
    }
    public static int maxNumberOfSteps(int month, HashMap<Integer, MonthData> monthData) {
        int max = 0;
        for (int i = 0; i < 30; i++) {
            if(monthData.get(month).dayData.get(i) != null && monthData.get(month).dayData.get(i) > max) {
                max = monthData.get(month).dayData.get(i);
            }
        }
        return max;
    }

    public static HashMap bestSeries(int month, HashMap<Integer, MonthData> monthData) {
        ArrayList<Integer> bestSeries = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> bestSession = new HashMap<>();
        ArrayList<Integer> best = new ArrayList<>();
        int goal = StepTracker.goal;
        int counter = 0;
        for(int i = 0; i < 30; i++) {
            if(monthData.get(month).dayData.get(i) != null && monthData.get(month).dayData.get(i) > goal) {
                counter++;
            } else if(((monthData.get(month).dayData.get(i) == null) || (monthData.get(month).dayData.get(i) != null && monthData.get(month).dayData.get(i) < goal)) && (counter > 0)) {
                bestSeries.add(counter);
                counter = 0;
            }
        }
        counter = 0;
        if(bestSeries.size() > 0) {
            for (int i = 0; i < bestSeries.size(); i++) {
                for (int j = 0; j < 30; j++) {
                    if (monthData.get(month).dayData.get(j) != null && monthData.get(month).dayData.get(j) > goal) {
                        best.add(monthData.get(month).dayData.get(j));
                        counter++;
                    } else if (((monthData.get(month).dayData.get(j) == null) || (monthData.get(month).dayData.get(j) != null && monthData.get(month).dayData.get(i) < goal)) && (counter > 0)) {
                        bestSession.put(bestSeries.get(i), best);
                        i++;
                        counter = 0;
                        best = new ArrayList<>();
                    }
                }
            }
        }
        return bestSession;
    }

    public static int getKeyWithMaxValue(int month, HashMap<Integer, MonthData> monthData){
        ArrayList<Integer> bestSeries = new ArrayList<>();
        int counter = 0;
        for(int i = 0; i < 30; i++) {
            if(monthData.get(month).dayData.get(i) != null && monthData.get(month).dayData.get(i) > goal) {
                counter++;
            } else if(((monthData.get(month).dayData.get(i) == null) || (monthData.get(month).dayData.get(i) != null && monthData.get(month).dayData.get(i) < goal)) && (counter > 0)) {
                bestSeries.add(counter);
                counter = 0;
            }
        }
        int max = 0;
        if(bestSeries.size() > 0) {
            max = bestSeries.get(0);
            for (int i = 0; i < bestSeries.size(); i++) {
                if (bestSeries.get(i) > max) {
                    max = bestSeries.get(i);
                }
            }
        }
        return max;
    }

    public static void changeGoal(int goal) {
        StepTracker.goal = goal;
    }
}
