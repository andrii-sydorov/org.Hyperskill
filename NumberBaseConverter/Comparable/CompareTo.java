package NumberBaseConverter.Comparable;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CompareTo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List <Person> person = new ArrayList<>();
		System.out.println("Enter your's person:");
		String[] p1 = sc.nextLine().split("-");
		String[] p2 = sc.nextLine().split("-");
		String[] p3 = sc.nextLine().split("-");
		person = buildList(p1, p2, p3);
		Collections.sort(person);
		printCollections(person);
	}
	
	private static void printCollections(List<Person> person) {
		for (Person p : person) {
			System.out.println(p.getName() + " " + p.getAge() + " " + p.getHeight() + " " + p.getWeigt());
		}
		
	}

	private static List<Person> buildList (String[]... p) {
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < p.length; i++) {
				persons.add(new Person(p[i][0], Integer.valueOf(p[i][1]), Integer.valueOf(p[i][2]), Integer.valueOf(p[i][3])));
		}
		return persons;
	}
	
	public static class Person implements Comparable<Person> {
		
		private int age;
		private String name;
		private int height;
		private int weight;
		
		public Person(String name, int age, int height, int weight) {
			this.name = name;
			this.age = age;
			this.height = height;
			this.weight = weight;
		}
		
		public int getAge() {
			return this.age;
		}
		
		public int getWeigt() {
			return this.weight;
		}
		
		public int getHeight() {
			return this.height;
		}
		
		public String getName() {
			return this.name;
		}
		
		@Override
		public int compareTo(Person otherPerson) {
			return name.equals(otherPerson.getName()) ? age == otherPerson.getAge() ? 0 : age < otherPerson.getAge() ? -1 : 1 : name.compareTo(otherPerson.getName()); 
		}
	}

}
