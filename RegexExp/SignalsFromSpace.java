package RegexExp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Replacing characters
 * 
 * Signal from space
 * 
 * Our satellite caught a signal from space. It is an array of strings but we
 * can't read the messages because there are too many disturbances in the form
 * of ~ symbols. Your task is to implement a method that takes an array of
 * strings as a parameter, removes the ~ symbol from each string, and returns
 * the result.
 * 
 * Sample input 1:
 * 4~ 8~ ~~15~ 16~ 2~~3 ~~4~~*~
 * Sample output 1:
 * 4 8 15 16 23 4*
 * 
 * Sample input 2:
 * ~H~ow d~~if~fic~~ul~~t~ i~~t ~is~ to ma~~ke p~opco~rn in ~ze~ro gr~av~it~y.
 * Sample output 2:
 * How difficult it is to make popcorn in zero gravity.
 * 
 * @author SMD_ASY
 *
 */

public class SignalsFromSpace {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String[] encryptedSpaceSignalArray = scanner.nextLine().split("\\s");
        scanner.close();
        String[] spaceSignalArray = decipherCosmicSignal(encryptedSpaceSignalArray);
        StringBuilder spaceMessage = new StringBuilder();
        for (String signal : spaceSignalArray) {
            spaceMessage.append(signal).append("\s");
        }
        System.out.println(spaceMessage.toString().trim());
        // 4~ 8~ ~~15~ 16~ 2~~3 ~~4~~*~
    }

    public static String[] decipherCosmicSignal(String[] spaceSignalArray) {
        for (int i = 0; i < spaceSignalArray.length; i++) {
            Pattern p = Pattern.compile("~+");
            Matcher m = p.matcher(spaceSignalArray[i]);
            if (m.find()) {
                spaceSignalArray[i] = m.replaceAll("");
            }
            // spaceSignalArray[i] = spaceSignalArray[i].replaceAll("`+","");
        }
        return spaceSignalArray;
    }

}