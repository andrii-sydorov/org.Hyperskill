package projects.Medium.SmartCalculator.Stage06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// also working

public class AnotherVariable {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();
        boolean isRunning = true;
        while (isRunning) {
            String command = sc.nextLine().trim();
            if (command.isEmpty()) {
                continue;
            }

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

            String invalidData = dataAnalyze(command);
            if (invalidData.length() > 0) {
                System.out.println(invalidData);
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

            String[] arr = command.split("\\s+");
            p = Pattern.compile("\\d+(\\+|-)+|\\d+\\s+\\d|[\\\\!?:;$]|[a-zA-Z]+[-+]");
            m = p.matcher(command);
            if (m.matches() || m.find()) {
                System.out.println("Invalid expression");
            } else if (arr.length != 0) {
                List<String> ls = makeDataInput(arr);
                int sum = calculateSum(ls, map);
                System.out.println(sum);
            }
        }
        System.out.println("Bye!");
        sc.close();
    }

    public static String dataAnalyze(String s) {
        String[] arr = s.split("=");
        String ans = "";

        Pattern p = Pattern.compile("\\d+\\s*=\\s*\\d+");
        Matcher m = p.matcher(s);
        if (m.find()) {
            ans = "Invalid assignment";
        }
        
        p = Pattern.compile("[a-zA-Z]+\\d+|[а-яА-Я]+");
        m = p.matcher(arr[0]);
        if (m.find()) {
            ans = "Invalid identifier";
        }

        if (arr.length > 1) {
            p = Pattern.compile("\\s*[a-zA-Z]+\\d+|\\d+[a-zA-Z]+");
            m = p.matcher(arr[1]);
            if (m.find()) {
                ans = "Invalid assignment";
            }
        }
        return ans;
    }

    public static boolean searchMenu(String command) {
        String ans = "";
        Pattern p = Pattern.compile("/[a-zA-Z]*");
        Matcher m = p.matcher(command);
        if (m.matches()) {
            if (command.equals("/help")) {
                ans = "The program calculates the sum of numbers";
            } else if (command.equals("/exit")) {
                ans = "";
            } else {
                ans = "Unknown command";
            }
        }
        if (ans.length() > 0) {
            System.out.println(ans);
            return false;
        }
        return true;
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
