package tp01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * SDD Seance TP 1 :
 *
 * @author <a href="mailto:Frederic.Guyomarch@univ-lille1.fr">Frédéric Guyomarch</a>
 * IUT-A, Universite de Lille, Sciences et TEchnologies
 */
public class Sort {

	public static Counter c = new Counter();

	public static int [] generateRdmIntArray(int n, int min, int max){
		int[] tab = new int[n];
		Random rand = new Random();
		for(int i=0; i<n; i++) {
			tab[i] = min + rand.nextInt(max - min);
		}
		return tab;
	}

	static void printArray(int [] tab) {
		System.out.println(Arrays.toString(tab));	
	}

	public static void insertSort(int [] tab) {
		for (int i = 1; i < tab.length; i++) { 
			c.incComp();
			int key = tab[i]; 
			int j = i - 1;
			while (j >= 0 && tab[j] > key) {
				c.incComp();
				tab[j + 1] = tab[j];
				c.incPerm();
				j = j - 1;
			}
			tab[j + 1] = key; 
		}
	}

	public static void selectSort(int [] tab){
		int iEnd = tab.length;
		for(int iBeg = 0; iBeg < tab.length - 1; iBeg++) {
			c.incComp();
			int iMin = iBeg;
			for(int i = iBeg + 1; i < iEnd; i++) {
				c.incComp();
				if(tab[i] < tab[iMin]) {
					iMin = i;
				}
				c.incComp();
			}
			swap(tab, iBeg, iMin);
			c.incPerm();	
		}
	}

	public static void swap(int [] tab, int idx, int idx2){
		int tmp = tab[idx];
		tab[idx] = tab[idx2];
		tab[idx2] = tmp;
	}

	public static void bubbleSort(int [] tab){
		for(int i = 0; i < tab.length-1; i++) {
			c.incComp();
			for(int j = 0; j < tab.length-1; j++) {
				c.incComp();
				if(tab[j] > tab[j+1]) {
					swap(tab, j, j+1);
					c.incPerm();
				}
				c.incComp();
			}
		}
	}

	public static void main(String[] args) {
		
		c.reset();
		int[] tab = generateRdmIntArray(10,5,100);
		System.out.println("unsorted");
		printArray(tab);
		bubbleSort(tab);
		System.out.println("sorted");
		printArray(tab);
		System.out.println(c.toString());
	}

}

