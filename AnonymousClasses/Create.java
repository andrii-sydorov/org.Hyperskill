package AnonymousClasses;

/**
 * Implement Runnable 
 * 
 * Java has a standard interface named java.lang.Runnable
 * with the single method run. The method has no arguments and returns nothing.
 * 
 * You should implement the given method createRunnable that takes two
 * arguments: text and repeats. The method must return an instance of an
 * anonymous class implementing java.lang.Runnable. The overridden method of the
 * anonymous class should print the text to the standard output a specified
 * number of times (repeats).
 * 
 * Use the provided code template, do not write the standard interface
 * java.lang.Runnable.
 * 
 * For example, if the text is "Hello!" and repeats is 3, the output should be:
 * 
 * Hello! Hello! Hello!
 * 
 * @author SMD_ASY
 *
 */

public class Create {

	public static Runnable createRunnable(String text, int repeats) {
		return new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < repeats; i++) {
					System.out.println(text);
				}
			}

		};
	}
}
