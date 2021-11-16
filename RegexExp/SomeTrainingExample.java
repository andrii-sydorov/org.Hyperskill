package RegexExp;

public class SomeTrainingExample {

    public static void main(String[] args) {

        String st = "Humans are hardly the only interesting members of the animal kingdom. Research on the bodies and behaviors of our furry cousins can help scientists learn more about our own species’ evolution and cognition. And even when they don’t help unlock the ancient secrets of human ancestry, some animals are just too cute — or weird, or gross, or terrifying — not to get to know a little better.";
        System.out.println(countAn(st));
        String pattern = "(r|go|b)at";
        String[] testCases = {"bat", "rat","goat"};
        for (String s : testCases) {
            if (s.matches(pattern)) {
                System.out.println(s + " matches to pattern");
            }
        }
    }

    private static int countAn(String s) {
        String[] ar = s.split("\\s+");
        int ans = 0;
        for(String str : ar) {
            if(str.contains("an")) {
                ans++;
            }
        }
        return ans;
    }

}
