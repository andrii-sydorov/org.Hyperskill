package projects.Medium.SmartCalculator.Stage05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stage 5/8: Error!
 * 
 * Description
 * 
 * Now you need to consider the reaction of the calculator when users enter
 * expressions in the wrong format. The program only knows numbers, a plus sign,
 * a minus sign, and two commands. It cannot accept all other characters and it
 * is necessary to warn the user about this.
 * 
 * Objectives
 * 
 * - The program should print Invalid expression in cases when the given
 * expression has an invalid format. If a user enters an invalid command, the
 * program must print Unknown command. All messages must be printed without
 * quotes. The program must never throw an exception.
 * - To handle incorrect input, you should remember that the user input that
 * starts with / is a command, in other situations, it is an expression.
 * - Like before, /help command should print information about your program.
 * When the command /exit is entered, the program must print Bye! , and then stop.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (>) represents the user input.
 * 
 * > 8 + 7 - 4
 * 11
 * > abc
 * Invalid expression
 * > 123+
 * Invalid expression
 * > +15
 * 15
 * > 18 22
 * Invalid expression
 * >
 * > -22
 * -22
 * > 22-
 * Invalid expression
 * > /go
 * Unknown command
 * > /exit
 * Bye!
 * 
 * @author SMD_ASY
 *
 */

public class Error {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String command = sc.nextLine();
            Pattern p = Pattern.compile("/[a-zA-Z]*");
            Matcher m = p.matcher(command);
            if (m.matches()) {
                if (command.equals("/help")) {
                    System.out.println("The program calculates the sum of numbers");
                } else if (command.equals("/exit")) {
                    isRunning = false;
                } else {
                    System.out.println("Unknown command");
                }
                continue;
            }
            p = Pattern.compile("[a-zA-Z]+|\\d+(\\+|-)+|\\d+\\s+\\d|[\\\\!?:;$]");
            m = p.matcher(command);
            if (m.matches() || m.find()) {
                System.out.println("Invalid expression");
            } else if (command.equals("")) {
                // empty line
            } else if (command.length() != 0) {
                List<String> ls = makeDataInput(command);
                int sum = calculateSum(ls);
                System.out.println(sum);
            }
        }
        System.out.println("Bye!");
        sc.close();
    }

    public static int calculateSum(List<String> ls) {
        int sum = Integer.valueOf(ls.get(0));
        for (int i = 1; i < ls.size(); i += 2) {
            switch (ls.get(i)) {
                case "+":
                    sum += Integer.valueOf(ls.get(i + 1));
                    break;
                case "-":
                    sum -= Integer.valueOf(ls.get(i + 1));
                    break;
            }
        }
        return sum;
    }

    public static List<String> makeDataInput(String command) {
        List<String> ls = new ArrayList<>();
        String[] arr = command.split("\\s+");
        Pattern p = Pattern.compile("[+-]?\\d+");
        for (int i = 0; i < arr.length; i++) {
            Matcher m = p.matcher(arr[i]);
            if (m.matches()) {
                ls.add(arr[i]);
            } else if (arr[i].startsWith("+")) {
                ls.add("+");
            } else if (arr[i].startsWith("-")) {
                String toAdd = arr[i].length() % 2 == 0 ? "+" : "-";
                ls.add(toAdd);
            }
        }
        return ls;
    }

    public static void goodSolution(String... args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String line = sc.nextLine().trim();

            switch (line) {
                case "/exit":
                    System.out.println("Bye!");
                    return;

                case "/help":
                    String msg = "The program calculates the sum and difference of numbers. \n" +
                            "Sequence of signs transforms into one, e.g. 3 --- 3 -> 3 - 3 -> 0.";

                    System.out.println(msg);
                    continue;

                case "":
                    break;

                default:
                    Long sum = 0L;
                    String unknownCommand = "(/\\S+)";
                    String invalidExpression = "([a-zA-Z]+|\\D+$|\\d+\\s+\\d+)";
                    String sign = "([+-]+)";
                    String digits = "(\\d+)";
                    String patternBuild = String.format("%s|%s|%s|%s",
                            unknownCommand,
                            invalidExpression,
                            sign,
                            digits);

                    Pattern pattern = Pattern.compile(patternBuild);
                    Matcher matcher = pattern.matcher(line);
                    boolean minus = false;
                    boolean wrong = false;

                    while (matcher.find()) {
                        if (matcher.group(1) != null) {
                            System.out.println("Unknown command");
                            wrong = true;
                            break;
                        }

                        if (matcher.group(2) != null) {
                            System.out.println("Invalid expression");
                            wrong = true;
                            break;
                        }

                        if (matcher.group(3) != null) {
                            int countMinus = matcher.group(3).replaceAll("[^-]", "").length();
                            minus = countMinus % 2 == 1 ? true : false;
                        }

                        if (matcher.group(4) != null) {
                            Long num = Long.valueOf(matcher.group(4));
                            sum += minus ? -num : num;
                        }
                    }

                    if (!wrong) {
                        System.out.println(sum);
                    }

                    break;
            }
        }
    }

}
