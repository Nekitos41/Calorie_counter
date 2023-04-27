package sprint_1.calorie_counter;

public class Converter {

    public float getDistance(int amountOfSteps) {
        float lengthOfStep = 0.00075f;
        return lengthOfStep * amountOfSteps;
    }

    public float getKilocalories(int amountOfSteps) {
        float amountOfCaloriesPerStep = 50;
        int amountOfCaloriesInKilocalorie = 1000;
        return amountOfCaloriesPerStep * amountOfSteps / amountOfCaloriesInKilocalorie;
    }
}
