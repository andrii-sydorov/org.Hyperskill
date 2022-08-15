package Comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Probe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Message> messages = new ArrayList<>();

		messages.add(new Message("Hello"));
		messages.add(new Message("humans!"));
		messages.add(new Message("We"));
		messages.add(new Message("came"));
		messages.add(new Message("with"));
		messages.add(new Message("peace!"));

		Comparator<Message> cmp1 = (x, y) -> x.getContest().compareTo(y.getContest());
		messages.sort(cmp1);
		messages.forEach(x -> System.out.println(x));

		Comparator<Message> cmp2 = new Comparator<Message>() {
			@Override
			public int compare(Message m1, Message m2) {
				return m1.getContest().length() > m2.getContest().length() ? 1
						: m1.getContest().length() < m2.getContest().length() ? -1 : 0;
			}
		};
		messages.sort(cmp2);
		messages.forEach(x -> System.out.println(x));

	}

}

class Message {

	private String contest;

	public Message(String contest) {
		this.contest = contest;
	}

	public String getContest() {
		return contest;
	}

	public void setContest(String contest) {
		this.contest = contest;
	}

	@Override
	public String toString() {
		return this.contest;
	}

}
