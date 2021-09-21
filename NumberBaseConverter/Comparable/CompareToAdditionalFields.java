package NumberBaseConverter.Comparable;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CompareToAdditionalFields {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		List<Article> ls = new ArrayList<>();
		String str = sc.nextLine();
		while(str.length() != 0) {
			createList(ls, str);
			str = sc.nextLine();
		}
		Collections.sort(ls);
		printListOfArticle(ls);
	}
	
	private static void printListOfArticle(List<Article> ls) {
		for (Article ar : ls) {
			System.out.println(ar.getTitle() + " " + ar.getSize());
		}
	}
	
	private static void createList(List<Article> ls, String str) {
		String[] articleData = str.split("-");
		ls.add(new Article(articleData[0], Integer.parseInt(articleData[1])));
	}

}

class Article implements Comparable<Article> {
	
	private String title;
	private int size;
	
	public Article(String title, int size) {
		this.title = title;
		this.size = size;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getSize() {
		return this.size;
	}
	
	@Override
	public int compareTo(Article otherArticle) {
		return size == otherArticle.getSize() ? title.compareTo(otherArticle.getTitle()) : size > otherArticle.getSize() ? 1 : -1;
	}
}
