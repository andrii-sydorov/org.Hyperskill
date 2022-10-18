package SimpleSearchEngine.Stage06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Stage 6/6: stage6
 * Description
 * Now let's Improve your search engine to make it support complex queries
 * containing word sequences and use several strategies that determine how to
 * match data.
 * 
 * Objectives
 * In this stage, your program should be able to use such searching strategies
 * as ALL, ANY, and NONE.
 * 
 * Take, for example, these six sample lines:
 * 
 * Dwight Joseph djo@gmail.com
 * Rene Webb webb@gmail.com
 * Katie Jacobs
 * Erick Harrington harrington@gmail.com
 * Myrtle Medina
 * Erick Burgess
 * 
 * If the strategy is ALL, the program should print lines containing all the
 * words from the query.
 * 
 * Query:
 * Harrington Erick
 * 
 * Result:
 * Erick Harrington harrington@gmail.com
 * 
 * If the strategy is ANY, the program should print the lines containing at
 * least one word from the query.
 * 
 * Query:
 * Erick Dwight webb@gmail.com
 * 
 * Result:
 * Erick Harrington harrington@gmail.com
 * Erick Burgess
 * Dwight Joseph djo@gmail.com
 * Rene Webb webb@gmail.com
 * 
 * If the strategy is NONE, the program should print lines that do not contain
 * words from the query at all:
 * 
 * Query:
 * djo@gmail.com ERICK
 * 
 * Result:
 * Katie Jacobs
 * Myrtle Medina
 * Rene Webb webb@gmail.com
 * 
 * All listed operations are implemented in the inverted index. The results
 * should not contain duplicates.
 * 
 * Do not forget to use methods to decompose your program.
 * 
 * Example
 * The lines that start with > represent the user input. Note that these symbols
 * are not part of the input.
 * 
 * === Menu ===
 * 1. Find a person
 * 2. Print all persons
 * 0. Exit
 * > 1
 * 
 * Select a matching strategy: ALL, ANY, NONE
 * > ANY
 * 
 * Enter a name or email to search all suitable people.
 * > Katie Erick QQQ
 * 
 * 3 persons found:
 * Katie Jacobs
 * Erick Harrington harrington@gmail.com
 * Erick Burgess
 * 
 * @author SMD_ASY
 *
 */

public class SearchStrategies {

    public static List<String> data = new ArrayList<>();
    public static Map<String, List<Integer>> mapWithData = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            map.put(args[i], args[i + 1]);
        }
        String name = map.get("--data");
        buildData(name);
        buildMenu(sc);
        sc.close();
    }

    private static void printMenu() {
        System.out.println("=== Menu ===");
        for (Options op : Options.values()) {
            System.out.println(op);
        }
    }

    private static void buildMenu(Scanner sc) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            int userChoice = Integer.valueOf(sc.nextLine());
            System.out.println();
            switch (userChoice) {
                case 0:
                    System.out.println("Bye!");
                    isRunning = false;
                    break;
                case 1:
                    findPerson(sc);
                    break;
                case 2:
                    printPeople();
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
            System.out.println();
        }
    }

    private static void printPeople() {
        System.out.println("=== List of people ===");
        for (String s : data) {
            System.out.println(s);
        }
    }

    private static void findPerson(Scanner sc) {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategyName = sc.nextLine();
        SelectStrategy selectStrategy = null;
        switch (strategyName) {
            case "ALL":
                selectStrategy = new SelectStrategy(new All());
                break;
            case "ANY":
                selectStrategy = new SelectStrategy(new Any());
                break;
            case "NONE":
                selectStrategy = new SelectStrategy(new None());
                break;
            default:
                System.out.println("Incorrect strategy name");
                return;
        }
        System.out.println();
        System.out.println("Enter a name or email to search all suitable people.");
        String name = sc.nextLine().toLowerCase();
        System.out.println();
        Map<String, List<Integer>> copyOfMap = new HashMap<>();
        copyOfMap.putAll(mapWithData);
        List<String> ls = selectStrategy.getResult(name, data, copyOfMap);
        if (ls != null) {
            System.out.printf("%d person%s found:\n", ls.size(), ls.size() == 1 ? "" : "s");
            for (String s : ls) {
                System.out.println(s);
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

    private static void buildData(String name) {
        File f = new File(name);
        try (Scanner innerScanner = new Scanner(f)) {
            int index = 0;
            while (innerScanner.hasNext()) {
                String s = innerScanner.nextLine();
                data.add(s);
                String[] arrayOfData = s.toLowerCase().split("\\s+");
                index++;
                for (int i = 0; i < arrayOfData.length; i++) {
                    if (mapWithData.keySet().contains(arrayOfData[i])) {
                        mapWithData.get(arrayOfData[i]).add(index);
                    } else {
                        List<Integer> ls = new ArrayList<>();
                        ls.add(index);
                        mapWithData.put(arrayOfData[i], ls);
                    }
                }

            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("The data were not downloaded");
        }
    }
}

enum Options {

    SEARCH("Find a person", 1), PRINT("Print all data.", 2), EXIT("Exit.", 0);

    private String message;
    private int index;

    Options(String message, int index) {
        this.message = message;
        this.index = index;
    }

    public String toString() {
        return this.index + ". " + this.message;
    }

    public int getIndex() {
        return this.index;
    }

}

class SelectStrategy {

    public SearchStrategy strategy;

    public SelectStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public List<String> getResult(String strategyName, List<String> listWithData,
            Map<String, List<Integer>> mapWithdata) {
        return strategy.searchResults(strategyName, listWithData, mapWithdata);
    }
}

interface SearchStrategy {

    public List<String> searchResults(String toFind, List<String> listWhereFind, Map<String, List<Integer>> copyOfMap);

}

class All implements SearchStrategy {

    @Override
    public List<String> searchResults(String toFind, List<String> listWhereFind,
            Map<String, List<Integer>> copyOfMap) {
        String[] toSearch = toFind.split("\\s+");
        List<Integer> listOfIndexes = new ArrayList<>();
        boolean firstTurns = true;
        for (String s : toSearch) {
            if (copyOfMap.get(s) == null) {
                continue;
            }
            if (firstTurns) {
                listOfIndexes = copyOfMap.get(s);
                firstTurns = false;
            } else {
                listOfIndexes.retainAll(copyOfMap.get(s));
            }
        }
        if (listOfIndexes.isEmpty()) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for (Integer i : listOfIndexes) {
            result.add(listWhereFind.get(i - 1));
        }
        return result;
    }

}

class Any implements SearchStrategy {

    @Override
    public List<String> searchResults(String toFind, List<String> listWhereFind, Map<String, List<Integer>> copyOfMap) {
        String[] toSearch = toFind.split("\\s+");
        Set<Integer> setOfIndexes = new HashSet<>();
        for (String s : toSearch) {
            List<Integer> toAdd = copyOfMap.get(s);
            if (toAdd != null) {
                setOfIndexes.addAll(toAdd);
            }
        }
        if (setOfIndexes.isEmpty()) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for (Integer i : setOfIndexes) {
            result.add(listWhereFind.get(i - 1));
        }
        return result;
    }

}

class None implements SearchStrategy {

    @Override
    public List<String> searchResults(String toFind, List<String> listWhereFind, Map<String, List<Integer>> copyOfMap) {
        String[] toSearch = toFind.split("\\s+");
        Set<Integer> setOfIndexes = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (String s : toSearch) {
            List<Integer> ls = copyOfMap.get(s);
            if (ls == null) {
                continue;
            }
            int[] ar = copyOfMap.get(s).stream().mapToInt(i -> i).toArray();
            for (Integer i : ar) {
                for (Map.Entry<String, List<Integer>> entr : copyOfMap.entrySet()) {
                    entr.getValue().remove(i);
                }
            }
        }
        for (Map.Entry<String, List<Integer>> entr : copyOfMap.entrySet()) {
            setOfIndexes.addAll(entr.getValue());
        }
        for (Integer i : setOfIndexes) {
            result.add(listWhereFind.get(i - 1));
        }
        return result;
    }

}
