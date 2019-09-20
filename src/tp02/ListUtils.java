package tp02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListUtils{
	
	public static List<Integer> genereRdmIntList() {
		List<Integer> list = new ArrayList<>();
		for(int i = (int) Math.random() * 30 + 1; i > 0 ; i--) list.add((int) Math.random() * 99 + 1);
		return list;
	}
	
	public static void affiche(List<Integer> l){
		String res = "";
		for(Iterator<Integer> iterator = l.iterator(); iterator.hasNext();) {
			res += iterator.next();
			if(iterator.hasNext()) res += " -> ";
		}
		System.out.println(res);
	}

	public static void afficheInverse(List<Integer> l) {
		String res = "";
		for(ListIterator<Integer> iterator = l.listIterator(l.size()); iterator.hasPrevious();) {
			res += iterator.previous();
			if(iterator.hasPrevious()) res += " -> ";
		}
		System.out.println(res);
	}
	
	public static int somme(List<Integer> l) {
		int somme = 0;
		for(Iterator<Integer> iterator = l.iterator(); iterator.hasNext();) {
			somme += iterator.next();
		}
		return somme;
	}
	
	public static int moyenne(List<Integer> l) {
		return somme(l)/l.size();
	}
	
	public static int max(List<Integer> l) {
		ListIterator<Integer> iterator = l.listIterator();
		int max = iterator.next();;
		while(iterator.hasNext()) {
			if(max < iterator.next()) {
				max = iterator.previous();
				iterator.next();
			}
		}
		return max;
	}
	
	public static int min(List<Integer> l) {
		ListIterator<Integer> iterator = l.listIterator();
		int min = iterator.next();
		while(iterator.hasNext()) {
			if(min > iterator.next()) {
				min = iterator.previous();
				iterator.next();
			}
		}
		return min;
	}
	
	public static List<Integer> positions(List<Integer> l, int n){
		List<Integer> list = new ArrayList<>();
		ListIterator<Integer> iterator = l.listIterator();
		while(iterator.hasNext()) {
			if(iterator.next() == n) {
				list.add(iterator.previousIndex());
			}
		}
		return list;
	}
	
	public static List<Integer> paire(List<Integer> l){
		List<Integer> list = new ArrayList<>();
		ListIterator<Integer> iterator = l.listIterator();
		while( iterator.hasNext()) {
			if(iterator.next()%2 == 0) {
				list.add(iterator.previous());
				iterator.next();
			}
		}
		return list;
	}
	
	public static boolean estTrie(List<Integer> l) {
		ListIterator<Integer> iterator = l.listIterator();
		int element = iterator.next();
		while(iterator.hasNext()) {
			if(element > iterator.next()) {
				return false;
			}
			iterator.next();
		}
		return true;
	}
	
	public static List<Integer> trie(List<Integer> l) {
		Collections.sort(l);
		return l;
	}	
}