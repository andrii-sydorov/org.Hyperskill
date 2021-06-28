package TicTacToe_04;

import java.util.Random;

public class IIUserEasy implements User {
	
	Game g;
	
	public IIUserEasy(Game g) {
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
	}

}
