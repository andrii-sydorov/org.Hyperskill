package StringBuilder;

/**
 * Here you need to prepare a StringBuilder that produces all English letters in
 * the uppercase (A-Z). All the letters should be separated by one space, but
 * there shouldn't be a space after the last letter. Despite the possibility to
 * solve this problem without a StringBuilder at all, we highly recommend you to
 * use it to get more practice.
 * 
 * @author ASY
 *
 */

public class EnglishAlphabet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("A");
		int n = 25;
		for (int i = 0; i < n; i++) {
			sb.append(" ").append(Character.toString(sb.charAt(sb.length() - 2) + 1));
		}
		System.out.println(sb.toString());
	}
}
