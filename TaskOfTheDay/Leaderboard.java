package TaskOfTheDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * v
 * 
 * There is an application to create leaderbords of e-sports competitions. It
 * uses the Score class to represent a score of each player. This class has two
 * fields: player for the player's name and totalScore for that player's total
 * score. To build a leaderbord, the Score objects need to be compared. A Score
 * object is considered bigger than another Score if it's totalScore value is
 * bigger. If totalScore values of two Score objects are the same, such objects
 * must be compared by their player values. See the example below.
 * 
 * Report a typo
 * 
 * Sample Input 1:
 * 
 * Ann 162 Zipper 121 Flash 121 CoolDoge 200
 * 
 * Sample Output 1:
 * 
 * [Flash=121, Zipper=121, Ann=162, CoolDoge=200]
 * 
 * @author SMD_ASY
 *
 */

public class Leaderboard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		List<Score> scores = new ArrayList<>();
		while (sc.hasNextLine()) {
			String[] input = sc.nextLine().split(" ");
			if (input == null || input.length != 2) {
				break;
			}
			Score score = new Score(input[0], Integer.parseInt(input[1]));
			scores.add(score);
		}
		Collections.sort(scores);
		sc.close();
		System.out.println(scores);
	}

}

class Score implements Comparable<Score> {

	private int score;
	private String player;

	public Score(String player, int score) {
		this.score = score;
		this.player = player;
	}

	public int getScore() {
		return this.score;
	}

	public String getPlayer() {
		return this.player;
	}

	public String toString() {
		return player + '=' + score;
	}

	public int compareTo(Score score) {
		if (this.score != score.getScore()) {
			return this.score > score.getScore() ? 1 : -1;
		}
		return this.player.compareTo(score.getPlayer());
	}
}
