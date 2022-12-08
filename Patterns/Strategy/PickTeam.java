package Patterns.Strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Imagine that you're creating teams to organize events. You need a module for
 * your program that will select the people for these teams.
 * 
 * There are only two selection algorithms:
 * 
 * take every k-th person, or every person if k =1;
 * take the last k people, or the last person if k = 1.
 * You decided to use the strategy pattern in the module because new selection
 * algorithms will be added in the future. Also, the pattern allows you to
 * change the current algorithm at runtime.
 * 
 * The basic structure of classes is provided below, but it doesn't work
 * properly yet.
 * 
 * Your goal is to implement the following methods:
 * 
 * setAlgorithm and selectPersons methods of the class SelectionContext;
 * select of the class TakePersonsWithStepAlgorithm to take every k-th person
 * starting with the index 0 in the same order as in the input array (when k is
 * 3, then it must take 0, 3, 6, ... persons);
 * select of the class TakeLastPersonsAlgorithm to take the last k persons in
 * the same order as the input array.
 * Perhaps, you should add some fields to the classes as well.
 * 
 * Please do not change the class Person and the interface
 * PersonSelectionAlgorithm, and do not rename existing methods.
 * 
 * HINT: tests 1–4 check TakePersonsWithStepAlgorithm, tests 5–8 check
 * TakeLastPersonsAlgorithm. Do not forget to check your solution when the step
 * is 1 or the input array consists of a single element.
 * 
 * @author SMD_ASY
 *
 */

public class PickTeam {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        final Scanner scanner = new Scanner(System.in);

        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        final String[] configs = scanner.nextLine().split("\\s+");

        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }

        scanner.close();
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }

}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);
}

class SelectionContext {

    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        // write your code here
        this.algorithm = algorithm;
    }

    public Person[] selectPersons(Person[] persons) {
        // write your code here
        return this.algorithm.select(persons);
    }
}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {

    int step;

    public TakePersonsWithStepAlgorithm(int step) {
        // write your code here
        this.step = step;
    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        if (step == 1) {
            return persons;
        }
        List<Person> ls = new ArrayList<>();
        for (int i = 0; i < persons.length; i += step) {
            ls.add(persons[i]);
        }
        return ls.toArray(new Person[0]);
    }
}

class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {

    int count;

    public TakeLastPersonsAlgorithm(int count) {
        this.count = count;
    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        if (count == 1) {
            return new Person[] { persons[persons.length - 1] };
        }
        return Arrays.copyOfRange(persons, persons.length - count, persons.length);
    }
}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }
}
