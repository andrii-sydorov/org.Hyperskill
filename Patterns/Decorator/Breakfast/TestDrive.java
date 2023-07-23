package Patterns.Decorator.Breakfast;

/**
 * Breakfast Decorator
 * They say that breakfast is the most important meal of the day: if that's
 * true, it better be fancy. In this task, we offer you to decorate a slice of
 * bread for your perfect developer's morning. Do not forget about calories!
 * 
 * 
 * Sample Input:
 * 
 * Sample Output:
 * Cheese on top of Butter on top of Bread. kCal: 290
 * Jam on top of Butter on top of Bread. kCal: 370
 */

public class TestDrive {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Breakfast bagel = new Bread();
        bagel = new Butter(bagel);
        bagel = new Cheese(bagel);
        System.out.println(bagel.getSummary());

        Breakfast bun = new Bread();
        bun = new Butter(bun);
        bun = new Jam(bun);
        System.out.println(bun.getSummary());

    }

}

interface Breakfast {
    String getDescription();

    int getKcal();

    default String getSummary() {
        return getDescription() + ". kCal: " + getKcal();
    }
}

class Bread implements Breakfast {

    String description = "Bread";
    int kcal = 200;

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getKcal() {
        return this.kcal;
    }

}

class Jam implements Breakfast {

    private final Breakfast breakfast;

    public Jam(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    @Override
    public String getDescription() {
        return "Jam on top of " + breakfast.getDescription();
    }

    @Override
    public int getKcal() {
        return breakfast.getKcal() + 120;
    }

}

class Butter implements Breakfast {

    private final Breakfast breakfast;

    public Butter(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    @Override
    public String getDescription() {
        return "Butter on top of " + breakfast.getDescription();
    }

    @Override
    public int getKcal() {
        return breakfast.getKcal() + 50;
    }

}

class Cheese implements Breakfast {

    private final Breakfast breakfast;

    public Cheese(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    @Override
    public String getDescription() {
        return "Cheese on top of " + breakfast.getDescription();
    }

    @Override
    public int getKcal() {
        return breakfast.getKcal() + 40;
    }

}
