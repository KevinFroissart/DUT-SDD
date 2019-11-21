package ssd_ctp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/** Contrôle TP de M3103
 *  Implémentation d’une file de priorité, en utilisant un tableau trié.
 * @date 21/11/2019 DDMMYYYY
 * @author Kévin Froissart
 */


public class FileDePriorite<E> {
	
	private final int INITIAL_SIZE = 11;
	private int index;
	private E[] tab;
	private Comparator<E> comp;

	/**
	 * Collection qui utilise l’ordre naturel avec une capacité initiale de 11 éléments
	 */
	FileDePriorite(){
		index = 0;
		//on créer un tableau de taille 11
		tab = (E[]) new Collection[INITIAL_SIZE];
	}
	
	/**
	 * Collection initialisée avec les éléments de la collection 
	 * fournie en paramètre qui utilise leur ordre naturel
	 * @param C
	 */
	FileDePriorite (Collection<? extends E> c) {
		index = c.size();
		//on créer un tableau en fonction du nombre d'éléments présent dans c
		tab = (E[]) new Collection[c.size()];
		for(E element : c) {
			//on copie dans notre tableau tout les éléments présent dans c
			index++;
			tab[index] = (E) c;
		}
	}

	/**
	 * Collection qui utilise l’ordre naturel dont la capacité initiale est fournie en paramètre
	 * @param initialCapacity
	 */
	FileDePriorite (int initialCapacity) {
		index = 0;
		//on créer un tableau en fonction de l'entier donné en paramètre
		tab = (E[]) new Collection[initialCapacity];
	}
	
	/**
	 * Collection qui utilise l’ordre du Comparator et la capacité initiale fournie en paramètre
	 * @param initialCapacity
	 * @param comparator
	 */
	FileDePriorite (int initialCapacity, Comparator<? super E> comparator) {
		index = 0;
		//on créer un tableau en fonction de l'entier donné en paramètre
		tab = (E[]) new Collection[initialCapacity];
		//on utilise le comparator donné en paramètres
		comp = (Comparator<E>) comparator;
	}
	
	/**
	 * Collection initialisée avec les éléments de la collection fournie en paramètre, 
	 * en respectant l’ordre de celle-ci
	 * @param C
	 */
	FileDePriorite (SortedSet<? extends E> c) {
		index = c.size();
		//on créer un tableau de taille 11
		tab = (E[]) new Collection[c.size()];
		for(E element : c) {
			//on copie dans notre tableau tout les éléments triés présent dans c
			index++;
			tab[index] = (E) c;
		}
	}
	
	/**
	 * Méthode qui test si la file est vide ou non
	 */
	public boolean isEmpty() {
		return index == 0;
	}
	
	/**
	 * Méthode qui vide la liste
	 */
	public void clear() {
		index = 0;
	}
	
	/**
	 * Méthode qui renvoie la taille du tableau
	 * @return index
	 */
	public int size() {
		return tab.length;
	}
	
	/**
	 * Méthode qui double la taille du tableau
	 */
	public void doubleTaille() {
		//on créer un tableau temporaire avec une taille double de notre tableau initial
		E[] temp = (E[]) new Collection[tab.length*2];
		for(int i=0; i<tab.length; i++) {
			temp[i] = tab[i]; //on copie dans notre tableau temporaire les éléments de l'ancien tableau
		}
		//on remplace l'ancien tableau par le nouveau
		tab = temp;
	}
	
	/**
	 * Méthode qui divise la taille du tableau
	 * @throws FileDePrioriteReductionException 
	 */
	public void diviseTaille() throws FileDePrioriteReductionException {
		if(tab.length/2 < size()) throw new FileDePrioriteReductionException("Trop d'éléments pour réduire le tableau !");
		E[] temp = (E[]) new Collection[tab.length/2];
		for(int i=0; i<tab.length; i++) {
			temp[i] = tab[i]; //on copie dans notre tableau temporaire les éléments de l'ancien tableau
		}
		//on remplace l'ancien tableau par le nouveau
		tab = temp;
	}
	
	/**
	 * Méthode qui ajoute un element au tableau
	 * @param e
	 */
	public boolean add(E e) {
		if(e == null) throw new NullPointerException();
		if(e.getClass() != tab[0].getClass()) throw new ClassCastException();
		//on test si l'élément e est non null et compatible avec les autres éléments du tableau
		//si le tableau est trop petit on double sa taille et finalement on ajoute l'élément
		if(size() == tab.length) doubleTaille();
		tab[size()+1] = e;
		index++;
		return true;
		
		// recherche dichotomique a ajouter
	}
	
	/**
	 * Méthode qui ajoute un element au tableau en passant par add(E e)
	 * @param e
	 */
	public boolean offer(E element) {
		return add(element);
	}
	
	/**
	 * Méthode qui retourne et supprime le premier élément de la file
	 * @return e
	 */
	public E poll() {
		if(isEmpty()) return null;
		E e = tab[0];
		index--;
		//si la liste n'est pas nulle on retourne le premier élément et on réduit la taille de la liste de 1
		//on décale tout à gauche
		for(int i=0; i<=size(); i++) {
			if(i < size()) tab[i] = tab[i+1];
		}
		//si le nombre de cases utilisées dans le tableau et inférieur a la moitié de sa taille alors on divise sa taille par deux
		try {
			diviseTaille();
		} catch (FileDePrioriteReductionException e1) {
			e1.printStackTrace();
		}
		return e;
	}
	
	/**
	 * Méthode qui supprime la première occurence de l'élément donné en paramètre
	 * @param o
	 */
	public boolean remove(Object o) {
		boolean res = false;
		int indexSupp = 0;
		for(int i = 0; i < size(); i++) {
			//on cherche la première occurence de o et ce jusqu'a qu'on le trouve
			if(o.equals(tab[i]) && !res) {
				res = true;
				indexSupp = i;
				index--;
			}
			//si on la trouvé et que ce n'est pas le dernier élément de la liste
			//alors on décale de 1 vers la gauche tout les éléménts du tableau
			if(res && indexSupp != size()) {
				tab[i] = tab[i+1];
			}
		}
		try {
			diviseTaille();
		} catch (FileDePrioriteReductionException e1) {
			e1.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Méthode qui retourne le premier élément de la file
	 * @return tab[index]
	 */
	public E peek() {
		if(isEmpty()) return null;
		return tab[0];
	}
	
	/**
	 * Méthode qui retourne le premier élément de la file
	 * @return tab[index]
	 */
	public E element() {
		if(isEmpty()) throw new NoSuchElementException();
		return tab[index];
	}
	
	/**
	 * Méthode qui transforme une arraylist en tableau
	 * @return tab
	 */
	public Object[] toArray() {
		Object[] tab = (Object[]) new Collection[()];
		for(Object o : list) {
			
		}
	}
	
	public <T> T[] toArray(T[] a) {
		
	}
	
	

}



