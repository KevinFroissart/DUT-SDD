package tp01;

import java.util.ArrayList;
import java.util.Arrays;


public class Friend implements Comparable<Friend>{

	private int age;
	private String name;
	
	public Friend(int age, String name) {
		this.age = age;
		this.name = name;
	}


	public int compareTo(Friend o) {
		if(this.age > o.age) return 1;
		if(this.age == o.age) return 0;
		return -1;
	}

	public String toString() {
		return "Age : " + age + " , Nom : " + name;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Friend> tabF = new ArrayList<>();
		Friend f1 = new Friend(19, "Kévin");
		Friend f2 = new Friend(13, "Pattoche");
		Friend f3 = new Friend(15, "Melo");
		Friend f4 = new Friend(21, "Calvin");
		Friend f5 = new Friend(36, "Romain");
		Friend f6 = new Friend(34, "Robin");
		Friend f7 = new Friend(7, "Max");
		Friend f8 = new Friend(69, "Luicas");
		Friend f9 = new Friend(41, "Collin");
		
		tabF.add(f1);
		tabF.add(f2);
		tabF.add(f3);
		tabF.add(f4);
		tabF.add(f5);
		tabF.add(f6);
		tabF.add(f7);
		tabF.add(f8);
		tabF.add(f9);

		System.out.println(tabF);
		tabF.sort(null);
		System.out.println(tabF);
	}



	
}
