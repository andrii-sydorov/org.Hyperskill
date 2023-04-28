package RegexExp;

import java.util.Scanner;

/**
 * Check the essay
 * You have been asked to read and check a student's essay about the Eiffel
 * Tower. You've noticed some recurring spelling errors and decided to write a
 * program to correct these mistakes throughout the whole text.
 * 
 * Your program should make all of the following changes (in any context):
 * 
 * - Franse —> France
 * - Eifel tower —> Eiffel Tower
 * - 19th —> XIXth
 * - 20th —> XXth
 * - 21st —> XXIst
 * 
 * Sample Input 1:
 * The Eifel tower is a wrought-iron lattice tower on the Champ de Mars in Paris, Franse.
 * Sample Output 1:
 * The Eiffel Tower is a wrought-iron lattice tower on the Champ de Mars in
 * Paris, France.
 * 
 * @author SMD_ASY
 *
 */

public class CheckTheEssay {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String france = "Franse";
        String tower = "Eifel tower";
        String nine = "19th";
        String twenty = "20th";
        String twentyOne = "21st";
        System.out.println(text.replaceAll(france, "France").replaceAll(tower, "Eiffel Tower").replaceAll(nine, "XIXth")
                .replaceAll(twenty, "XXth").replaceAll(twentyOne, "XXIst"));
        scanner.close();
    }

}
