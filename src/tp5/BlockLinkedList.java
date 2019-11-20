package tp4;

import java.util.AbstractList;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BlockLinkedList<E> extends AbstractList<E>  implements List <E>{
	
	private ArrayNode arrayNode;
	private static final int DEFAULT_SIZE = 4;
	private int nodeSize;
	
	public BlockLinkedList (int taille) {
		if (taille%2!=0) {
			throw new IllegalArgumentException();
		} else {
			arrayNode=new ArrayNode(taille);
			this.nodeSize=taille;
		}
	}
	
	public BlockLinkedList () {
		this(DEFAULT_SIZE);
	}
	
	private class ArrayNode {
		E [] list;
		int n;
		ArrayNode nodeSuivant;
		
		@SuppressWarnings("unchecked")
		public ArrayNode (int taille, ArrayNode an) {
			list=(E[]) new Object [taille];
			n = 0;
			nodeSuivant = an;
		}
		public ArrayNode (int taille) {
			this(taille, null);
		}
		
		public void split() {
			nodeSuivant = new ArrayNode(list.length, nodeSuivant);
			System.arraycopy(list, list.length/2, nodeSuivant.list, 0, list.length/2);
			n /= 2;
			nodeSuivant.n = n;
		}
		public void add(E e) {
			add(n, e);
		}
		public void add(int ind, E e) {
			if(n==list.length)
				split();
			if(ind>n)
				nodeSuivant.add(ind-n, e);
			else {
				System.arraycopy(list, ind, list, ind+1, n-ind);
				list[ind] = e;
				++n;
			}
		}
	}

	@Override
	public int size() {
		int taille=0;
		ArrayNode array=this.arrayNode;
		
		while(array.nodeSuivant!=null) {
			taille += array.n;
			array=array.nodeSuivant;
		}
		return taille;
	}

	@Override
	public boolean isEmpty() {
		return arrayNode.n==0;	
	}

	@Override
	public void clear() {
		arrayNode = new ArrayNode(nodeSize);
	}
	
	private static<E> int indexOf(E[] tab, Object e) {
		int i = 0;
		for(;i<tab.length && !tab[i].equals(e);++i);
		return i==tab.length?-1:i;
	}
	
	private SimpleEntry<ArrayNode, Integer> localiser (Object element) {
		ArrayNode array = this.arrayNode;
		while(array.nodeSuivant!=null) {
			int i = indexOf(array.list, element);
			if (i!=-1) 
				return new SimpleEntry<BlockLinkedList<E>.ArrayNode, Integer>(array, i);
			array=array.nodeSuivant;
		}
		return null;
	}
	
	@Override
	public boolean contains(Object o) {
		return localiser(o)!=null;
	}
	
	@Override
	public int indexOf(Object o) {
		int ind = 0;
		ArrayNode array = this.arrayNode;
		while(array.nodeSuivant!=null) {
			int i = indexOf(array.list, o);
			if (i!=-1) 
				return ind + i;
			else
				ind += array.n;
			array=array.nodeSuivant;
		}
		return -1;
	}
	
	private SimpleEntry<ArrayNode, Integer> localiser(int index){
		ArrayNode array = this.arrayNode;
		index -= array.n;
		array=array.nodeSuivant;
		while(array!=null && index>array.n) {
			index -= array.n;
			array=array.nodeSuivant;
		}
		if (array==null)
			throw new IndexOutOfBoundsException();
		return new SimpleEntry<BlockLinkedList<E>.ArrayNode, Integer>(array, index);	
	}
	
	@Override
	public E get(int index) {
		SimpleEntry<ArrayNode, Integer> se = localiser(index);
		return se.getKey().list[se.getValue()];
	}
	
	@Override
	public E set(int index, E element) {
		SimpleEntry<ArrayNode, Integer> se = localiser(index);
		return se.getKey().list[se.getValue()];	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		ArrayList <?> list=(ArrayList) c;
		for(int i=0; i<c.size(); i++) {
			if (!this.contains(list.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	private ArrayNode getLastNode() {
		ArrayNode array = this.arrayNode;
		while(array.nodeSuivant!=null) {
			array=array.nodeSuivant;
		}
		return array;
	}
	
	@Override
	public boolean add(E e) {
		getLastNode().add(e);
		return true;
	}
	
	@Override
	public void add(int index, E element) {
/*		ArrayNode <E> array=this.arrayNode;
		ArrayNode <E> tmp;
		int compteur=0, indiceTab=0;
		
		while(array.getNodeSuivant()!=null && compteur!=index-1) {
			for (int i=0; i<array.getList().length; i++) {
				if (compteur==index-1) {
					indiceTab=i;
				}
				if (array.getList()[i]!=null) {
					compteur++;
				}
			}
			array=array.getNodeSuivant();
		}
		
		if (array.getList()[indiceTab]!=null) {
			tmp=array.getNodeSuivant();
			array.setNodeSuivant(new ArrayNode <E> (this.defautSize));
			array.getNodeSuivant().setNodeSuivant(tmp);
			
			if (index-1<this.defautSize/2) {
				System.arraycopy(array.getList(), 0, array.getNodeSuivant().getList(), 0, this.defautSize/2);
			} else if (index-1>this.defautSize/2) {
				System.arraycopy(array.getList(), (this.defautSize/2)-1, array.getNodeSuivant().getList(), 0, this.defautSize/2);
			} 
		}
		
		array.getList()[indiceTab]=element;	
	*/
	}
	
@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		ArrayList <E> list=(ArrayList) c;
		
		for(int i=0; i<list.size(); i++) {
			this.add((index+i)-1, list.get(i));
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		ArrayList <E> list=(ArrayList) c;
		
		for(int i=0; i<list.size(); i++) {
			this.add(list.get(i));
		}
		return true;
	}
	
	private String toString(E[] tab, int n) {
		StringBuffer rep=new StringBuffer();
		if(n>0)
			rep.append(tab[0]);
		for(int i=1; i<n; ++i)
			rep.append(", " + tab[i]);
		return rep.toString();
	}
	
	public String toString () {
		StringBuffer rep=new StringBuffer("[");
		ArrayNode array=this.arrayNode;
		if(array!=null) {
			//String nodeString = Arrays.toString(array.list);
			//rep.append(nodeString.substring(1, nodeString.length()-1));
			rep.append(toString(array.list, array.n));
			array=array.nodeSuivant;
		}
		while(array!=null) {
			rep.append(", ");
			//String nodeString = Arrays.toString(array.list);
			//rep.append(nodeString.substring(1, nodeString.length()-1));
			rep.append(toString(array.list, array.n));
			array=array.nodeSuivant;
		}
		rep.append("]");
		return rep.toString();
	}

}
