package Polymorphism;

/**
 * You've decided to create your own project and for that, you need to employ 1
 * programmer and 1 team lead. You have written the code for that, but it does
 * not work.
 * 
 * Your task is to find a mistake.
 * 
 * Note: don't rename methods or create your own classes and don't create new
 * objects. Don't use super and this.
 * 
 * Sample Input 1:
 * 
 * Time to code
 * 
 * Sample Output 1:
 * 
 * 1 programmer 
 * 1 teamlead
 * 
 * @author SMD_ASY
 *
 */

public class Test {

	public static void main(String[] args) {
		new Teamlead(1);
	}

	public static class Teamlead extends Programmer {

		private final int numTeamlead;

		public Teamlead(int numTeamlead) {
			super(numTeamlead);
			this.numTeamlead = numTeamlead;
			employ();
		}

		protected void employ() {
			System.out.println(numTeamlead + " teamlead");
		}
	}

	public static class Programmer {

		private final int numProgrammer;

		public Programmer(int numProgrammer) {
			this.numProgrammer = numProgrammer;
			employ();
		}

		private void employ() {
			System.out.println(numProgrammer + " programmer");
		}
	}

}
