package TP04;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import TP04.BlockLinkedList;






public class BlockLinkedList<E> implements List<E> {

	ArrayNode<E> first;
	final int TAILLE_MAX;
	
	public BlockLinkedList()
	{
		this(4);
	}
	
	public BlockLinkedList(int size)


	{
		if((size % 2) == 1)
			throw new IllegalArgumentException("La liste doit avoir une taille paire");
		
		TAILLE_MAX = size;
	}

	
	
	@Override
	public int size() {
		ArrayNode<E> node = first;
		int size = 0;
		while(node.next != null) {
			size += node.cardinal;
			node = node.next;
		}
		node = node.next;
		size += node.cardinal;
		return size;
	}

	@Override
	public boolean isEmpty() {
		return this.size() ==0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		
		this.first = new ArrayNode<E>();
		
	}

	@Override
	public E get(int index) {
		Iterator<E> it = new BlockLinkedListIterator<>();
		for (int i = 0; i != index && it.hasNext(); i++) {
			it.next();
		}
		
		return it.next();
	}

	@Override
	public E set(int index, E element) {
		Iterator<E> it = new BlockLinkedListIterator<>();
		for (int i = 0; i != index && it.hasNext(); i++) {
			// flemme, faut pas iterer ici
			return element;
		}
		
		return null;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return localize(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {

		
		String res = "[";
		ArrayNode<E> node = first;
		while(node.next != null) {
			res += node.toString();
			node = node.next;
		}
		return res += node.toString() + "]";
	}
	
	public int localize(Object o)
	{
		Iterator<E> it = new BlockLinkedListIterator<E>();
		int i = -1;
		
		while(!o.equals(it.next()) || it.hasNext())
		{
			i++;
		}
		
		return i;
	}
	/*****************************************************/

	class ArrayNode<T> {
		T[] tab;
		ArrayNode<T> next;
		int cardinal;

	
		@SuppressWarnings("unchecked")
		public ArrayNode() {
			this(((T[]) new Object[TAILLE_MAX]), null);
		}


		public ArrayNode(T[] tab){
			this(tab, null);
		}

		public ArrayNode(T[] tab, ArrayNode<T> next) {
			this.tab = tab;
			this.next = null;
			this.cardinal = 0;
		}

		@Override
		public String toString() {
			return Arrays.toString(tab);
		}
		
	}

	/*******************************************************/
	
	private class BlockLinkedListIterator<E> implements Iterator<E>
	{

		private ArrayNode<E> list;
		private int index = 0;
		
		@Override
		public boolean hasNext()
		{
			if(list == null)
				return false;
			if(list.tab[index] == null)
				return false;
			
			return true;
		}

		@Override
		public E next()
		{	
			int tmpIndex = index;
			
			while(list.tab[tmpIndex] == null)
			{
				if(tmpIndex >= TAILLE_MAX)
				{
					list = list.next;
					tmpIndex = 0;
				} else {
					tmpIndex++;
				}

			}
			index = tmpIndex;
			
			return list.tab[index];
		}
		
	}
}
