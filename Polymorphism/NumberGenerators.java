package Polymorphism;

/**
 * Here's a class hierarchy of magic number generators.
 * 

 * 
 * BaseNumberGenerator generator = new MagicNumberGenerator(10);
 * 
 * Enter the result of invoking generator.generate().
 * 
 * @author SMD_ASY
 *
 */

public class NumberGenerators {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BaseNumberGenerator generator = new MagicNumberGenerator(10);
		System.out.println(generator.generate());
	}

	public static class BaseNumberGenerator {

		protected int base;

		public BaseNumberGenerator(int base) {
			this.base = base;
		}

		public int generate() {
			return base + 11;
		}

	}

	public static class NumberGenerator extends BaseNumberGenerator {

		public NumberGenerator(int base) {
			super(base);
		}

		@Override
		public int generate() {
			return super.generate() + getNumber();
		}

		protected int getNumber() {
			return this.base - 7;
		}

	}

	public static class MagicNumberGenerator extends NumberGenerator {

		public MagicNumberGenerator(int base) {
			super(base);
		}

		@Override
		protected int getNumber() {
			return this.base + 7;
		}
	}

}
