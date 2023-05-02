package projects.Medium.SmartCalculator.Stage06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stage 6/8: Variables
 * 
 * Description
 * 
 * Now, the calculator will be able to store the results of previous
 * calculations. Do you have any idea how to do that? Of course! This can be
 * achieved by introducing variables. Storing results in variables and then
 * operating on them at any time is a very convenient function.
 * 
 * Objectives
 * 
 * So, your program should support variables. Use Map to store them.
 * Go by the following rules for variables:
 * 
 * - We suppose that the name of a variable (identifier) can contain only Latin
 *   letters.
 * - A variable can have a name consisting of more than one letter.
 * - The case is also important; for example, n is not the same as N.
 * - The value can be an integer number or a value of another variable.
 * - It should be possible to set a new value to an existing variable.
 * - To print the value of a variable you should just type its name.
 * 
 * The example below shows how variables can be declared and displayed.
 * 
 * > n = 3
 * > m=4
 * > a = 5
 * > b = a
 * > v= 7
 * > n =9
 * > count = 10
 * > a = 1
 * > a = 2
 * > a = 3
 * > a
 * 3
 * 
 * Incorrect spelling or declaration of variables should also throw an exception
 * with the corresponding message to the user:
 * 
 * - First, the variable is checked for correctness. If the user inputs an invalid
 * variable name, then the output should be "Invalid identifier".
 * 
 * > a2a
 * Invalid identifier
 * > n22
 * Invalid identifier
 * 
 * - If a variable is valid but not declared yet, the program should print
 * "Unknown variable".
 * 
 * > a = 8
 * > b = c
 * Unknown variable
 * > e
 * Unknown variable
 * 
 * - If an identifier or value of a variable is invalid during variable
 * declaration, the program must print a message like the one below.
 * 
 * > a1 = 8
 * Invalid identifier
 * > n1 = a2a
 * Invalid identifier
 * > n = a2a
 * Invalid assignment
 * > a = 7 = 8
 * Invalid assignment
 * 
 * Please note that the program should print "Invalid identifier" if the left
 * part of the assignment is incorrect. If the part after the "=" is wrong then
 * use the "Invalid assignment". First we should check the left side.
 * 
 * Handle as many incorrect inputs as possible. The program must never throw an
 * exception of any kind.
 * 
 * It is important to note, all variables must store their values between
 * calculations of different expressions.
 * 
 * Do not forget about previously implemented commands: /help and /exit.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (>) represents the user input.
 * 
 * > a = 3
 * > b= 4
 * > c =5
 * > a + b - c
 * 2
 * > b - c + 4 - a
 * 0
 * > a = 800
 * > a + b + c
 * 809
 * > BIG = 9000
 * > BIG
 * 9000
 * > big
 * Unknown variable
 * > /exit
 * Bye!
 * 
 * Tip: Think of your program as of a set of instructions to different cases.
 * For example, if it's a command, you perform one set of actions, or if it's an
 * assignment operation, then you perform other actions if it's an expression
 * that needs calculation it's also another thing. Refactoring your program at
 * this stage is not a bad idea!
 * 
 * @author SMD_ASY
 *
 */

public class Variables {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();
        boolean isRunning = true;
        while (isRunning) {
            String command = sc.nextLine().trim();
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

            p = Pattern.compile("[a-zA-Z]+\\s*=\\s*\\d+");
            m = p.matcher(command);
            String[] arg = command.split("=");
            if (m.matches()) {
                map.put(arg[0].trim(), arg[1].trim());
                continue;
            }

            p = Pattern.compile("[a-zA-Z]+\\s*=\\s*[a-zA-Z]+");
            m = p.matcher(command);
            if (m.matches()) {
                String value = map.get(arg[1].trim());
                if (value != null) {
                    map.put(arg[0].trim(), value);
                } else {
                    System.out.println("Unknown variable");
                }
                continue;
            }
            p = Pattern.compile("[a-zA-Z]+");
            m = p.matcher(command);
            if (m.matches()) {
                String value = map.get(arg[0].trim());
                if (value == null) {
                    System.out.println("Unknown variable");
                    continue;
                }
            }

            p = Pattern.compile("[a-zA-Z]+\\d+\\s*=|\\W+\\s*=\\s*\\d+");
            m = p.matcher(command);
            if (m.find()) {
                System.out.println("Invalid identifier");
                continue;
            }

            p = Pattern.compile("=\\s*[a-zA-Z]+\\d+|\\d+\\s*=\\s*\\d+|[a-zA-Z]+\\d+");
            m = p.matcher(command);
            if (m.find()) {
                System.out.println("Invalid assignment");
                continue;
            }

            String[] arr = command.split("\\s+");
            p = Pattern.compile("\\d+(\\+|-)+|\\d+\\s+\\d|[\\\\!?:;$]");
            m = p.matcher(command);
            if (m.matches() || m.find()) {
                System.out.println("Invalid expression");
            } else if (command.equals("")) {
                // empty line
            } else if (arr.length != 0) {
                List<String> ls = makeDataInput(arr);
                int sum = calculateSum(ls, map);
                System.out.println(sum);
            }
        }
        System.out.println("Bye!");
        sc.close();
    }

    public static int calculateSum(List<String> ls, Map<String, String> map) {
        int sum = map.get(ls.get(0)) != null ? Integer.valueOf(map.get(ls.get(0)))
                : Integer.valueOf(ls.get(0));
        for (int i = 1; i < ls.size(); i += 2) {
            switch (ls.get(i)) {
                case "+":
                    sum += map.get(ls.get(i + 1)) != null ? Integer.valueOf(map.get(ls.get(i + 1)))
                            : Integer.valueOf(ls.get(i + 1));
                    break;
                case "-":
                    sum -= map.get(ls.get(i + 1)) != null ? Integer.valueOf(map.get(ls.get(i + 1)))
                            : Integer.valueOf(ls.get(i + 1));
                    break;
            }
        }
        return sum;
    }

    public static List<String> makeDataInput(String[] arg) {
        List<String> ls = new ArrayList<>();
        Pattern p = Pattern.compile("[+-]?(\\d+|[a-zA-Z]+)");
        for (int i = 0; i < arg.length; i++) {
            Matcher m = p.matcher(arg[i]);
            if (m.matches()) {
                ls.add(arg[i]);
            } else if (arg[i].startsWith("+")) {
                ls.add("+");
            } else if (arg[i].startsWith("-")) {
                String toAdd = arg[i].length() % 2 == 0 ? "+" : "-";
                ls.add(toAdd);
            }
        }
        return ls;
    }

}
