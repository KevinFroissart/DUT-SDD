package tp4;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/** Liste chainee par block.
 * @author Froissart Kevin
 * @since 2019-10-4
 */

public class BlockLinkedList<E> implements List<E>{
	
	protected final int ARRAYNODE_DEFAULT_SIZE = 4;
	protected int eMax;
	protected ArrayNode<E> courant;
	protected ArrayNode<E> debut;
	protected ArrayNode<E> fin;
	protected List<ArrayNode<E>> list;
	protected int index = 0;
	
	public BlockLinkedList() {
		eMax = ARRAYNODE_DEFAULT_SIZE;
	}
	
	public BlockLinkedList(int n, E element) {
		if(n%2!=0) throw new IllegalArgumentException();
		eMax = n;
		courant = new ArrayNode<E>(element);
		fin = courant;
		debut = courant;
	}
	
	public class ArrayNode<E> {
		
		protected E element;
		protected ArrayNode<E> successeur;
		protected E[] tab;
		protected int n;
		
		public ArrayNode(E element, ArrayNode<E> next){
			this.element = element;
			successeur = next;
		}
		
		public ArrayNode(E element){
			this.element = element;
			successeur = null;
		}
		
		public void addElement(E element) {
			if(n++ < ARRAYNODE_DEFAULT_SIZE) {
				
			}
		}
		
		public E getElement() {
			return element;
		}
		
		public void setElement(E element) {
			this.element = element;
		}

		public ArrayNode<E> getSuccesseur() {
			return successeur;
		}

		public void setSuccesseur(ArrayNode<E> successeur) {
			this.successeur = successeur;
		}
		
		
	}
	
	
	public class BlockLinkedListIterator implements Iterator<E>{

		@Override
		public boolean hasNext() {
			if(!this.next().equals(null)) return true;
			return false;
		}

		@Override
		public E next() {
			if(hasNext()) return this.next();
			return null;
		}
	}


	@Override
	public int size() {
		
		return 0;
	}


	@Override
	public boolean isEmpty() {
		return false;
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
	public boolean add(E e) {

		return false;
	}


	@Override
	public boolean remove(Object o) {
			
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return 0;
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
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}
