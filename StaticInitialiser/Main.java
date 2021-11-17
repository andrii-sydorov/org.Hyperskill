package StaticInitialiser;

public class Main {

	public static void main(String[] args) {

		MagicNumber[] numbers = new MagicNumber[8];

		for (int i = 0; i < numbers.length; i++) {
			MagicNumber m = new MagicNumber(i);
			System.out.println(m.getNumber());
		}
	}

}

class MagicNumber {

	private static int[] numbers;
	private static int next = 0;

	private int number;

	static {
		numbers = new int[] { 1, 3, 7, 15, 31, 63 };
	}

	{
		this.number = numbers[next % numbers.length];
		next++;
	}

	public MagicNumber(int base) {
		this.number += base;
	}

	public int getNumber() {
		return this.number;
	}

}