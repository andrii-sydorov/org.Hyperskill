package TicTacToe_02;

import java.util.Random;

public class IIUser implements User {
	
	Game g;
	
	public IIUser(Game g) {
		this.g = g;
	}

	Random r = new Random();

	private String[] dataInput = new String[2];

	@Override
	public String[] getDataInput() {
		return this.dataInput;
	}

	@Override
	public void askUser() {
		System.out.println("Making move level \"easy\"");
		final int n = 2;
		for (int i = 0; i < n; i++) {
			dataInput[i] = String.valueOf(r.nextInt(g.numbersField) + 1);
		}
		System.out.println(this.dataInput[0] + " " + this.dataInput[1]);
	}

}
