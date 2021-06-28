package TicTacToe_01;

import java.util.Scanner;

public class HumanUser implements User {

	protected Scanner sc;
	private String[] dataInput;

	public HumanUser(Scanner sc) {
		this.sc = sc;
	}
	
	/**
	 * asking user about the coordinates data, output String array
	 */
	public void askUser() {
		System.out.print("Enter the coordinates: ");
		this.dataInput = sc.nextLine().trim().split(" ");
	}

	/**
	 * public method for the String data, that come from user
	 */
	public String[] getDataInput() {
		return this.dataInput;
	}

}
