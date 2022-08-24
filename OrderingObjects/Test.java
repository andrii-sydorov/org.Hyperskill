package OrderingObjects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Only for test sorting tables
 * @author SMD_ASY
 *
 */

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Team> teams = new ArrayList<>();
		teams.add(new Team("Bayern Munich", 100, 30, 10, 10));
		teams.add(new Team("Manchester United", 100, 25, 25, 0));
		teams.add(new Team("Real Madrid", 100, 33, 1, 19));
		teams.sort(Comparator.comparing(Team::getPoints).thenComparing(Team::getWins).thenComparing(Team::getDraws)
				.reversed());
		int place = 1;
		for (Team t : teams) {
			t.places = place;
			place++;
		}

		teams.forEach(x -> System.out.println(x));
	}

}

class Team {

	String name;
	int points;
	int wins;
	int draws;
	int losses;
	int places;

	public Team(String name, int points, int wins, int draws, int losses) {
		this.name = name;
		this.points = points;
		this.wins = wins;
		this.draws = draws;
		this.losses = losses;
	}

	public int getPoints() {
		return this.points;
	}

	public int getWins() {
		return this.wins;
	}

	public int getDraws() {
		return this.draws;
	}

	public int getLosses() {
		return this.losses;
	}

	public String toString() {
		return places + " " + name + " " + wins + " " + draws + " " + losses + " " + points;
	}
}
