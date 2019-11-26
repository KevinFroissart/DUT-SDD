package ssd_ctp;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/** Contrôle TP de M3103
 *  Implémentation d’une file de priorité, en utilisant un tableau trié.
 * @date 21/11/2019 DDMMYYYY
 * @author Kévin Froissart
 */


public class FileDePriorite<E> implements Collection<E> {

	private final int INITIAL_SIZE = 11;
	private int index;
	private E[] tab;
	@SuppressWarnings("unused")
	private Comparator<E> comp;
	
	// QUESTION 1

	/**
	 * Collection qui utilise l’ordre naturel avec une capacité initiale de 11 éléments
	 */
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings({ "unchecked", "unused" })
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
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings({ "unchecked", "unused" })
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

	// QUESTION 2
	
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

	// QUESTION 3
	
	/**
	 * Méthode qui double la taille du tableau
	 */
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
	public void diviseTaille() throws FileDePrioriteReductionException {
		if(tab.length/2 < size()) throw new FileDePrioriteReductionException();
		E[] temp = (E[]) new Collection[tab.length/2];
		for(int i=0; i<tab.length; i++) {
			temp[i] = tab[i]; //on copie dans notre tableau temporaire les éléments de l'ancien tableau
		}
		//on remplace l'ancien tableau par le nouveau
		tab = temp;
	}

	// QUESTION 4
	
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
		
		int idx = rechercher(e);
		
		tab[idx] = e;
		index++;
		return true;

	}

	/**
	 * Méthode de recherche dichotomique
	 * @param clé
	 * @param min
	 * @param max
	 * @param mil
	 * @return mil
	 */
	int rechercheDichotomique(E clé, int min, int max, int mil) {		
		
		if(comp.compare(clé, tab[mil]) == 0) return mil;
		if(comp.compare(clé, tab[mil]) > 0) {
			max = mil;
			mil = min + mil / 2;
			return rechercheDichotomique(clé, min, max, mil);
		}
		if(comp.compare(clé, tab[mil]) > 0) {
			min = mil;
			mil = min + mil / 2;
			return rechercheDichotomique(clé, min, max, mil);
		}
		return mil;
	}

	/**
	 * Méthode de recherche dichotomique
	 * @param clé
	 * @return rechercheDichotomique((E)clé, 0, size(), size()/2);
	 */
	public int rechercher(E clé) {
		return rechercheDichotomique(clé, 0, size(), size()/2);
	}

	// QUESTION 6

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
	
	// QUESTION 7

	/**
	 * Méthode qui retourne le premier élément de la file
	 * @return tab[index]
	 */
	public E peek() {
		if(isEmpty()) return null;
		return tab[0];
	}

	/**public
	 * Méthode qui retourne le premier élément de la file
	 * @return tab[index]
	 */
	public E element() {
		if(isEmpty()) throw new NoSuchElementException();
		return tab[0];
	}

	/**
	 * Méthode qui copie les éléments de tab dans un autre tableau de type Object
	 * @return res
	 */
	public Object[] toArray() {
		Object[] res = (Object[]) new Collection[size()];
		for(int i = 0; i<size(); i++) {
			//on copie les éléments de tab dans res
			res[i] = (Object) tab[i];
		}
		return res;
	}

	/**
	 * Méthode qui copie les éléments de tab dans un autre tableau de type T
	 * @return res
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		T[] res = (T[]) new Collection[size()];
		for(int i = 0; i<size(); i++) {
			//on copie les éléments de tab dans res
			res[i] = (T) tab[i];
		}
		return res;
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	// QUESTION 8

	/**
	 * Méthode qui vérifie si tout les éléments de la collection c sont présent dans le tableau
	 * @param c
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Méthode qui ajoute dans le tableau tout les éléments de la collection c
	 * @param c
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Méthode qui supprime dans tab tout les éléments de la collection c
	 * @param c
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Méthode supprime dans tab tout les éléments qui ne sont pas contenu dans c
	 * @param c
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	// QUESTION 9
	
	public class FileDePrioriteIterator<E> implements Iterator<E> {

		@Override
		public boolean hasNext() {

			return false;
		}

		@Override
		public E next() {
			if(hasNext()) return null;
			return null;
		}
		
	}

	
	/**
	 * Classe gérant l'exception FileDePrioriteReductionException
	 */
	@SuppressWarnings("serial")
	public static class FileDePrioriteReductionException extends Exception {

	}

}



