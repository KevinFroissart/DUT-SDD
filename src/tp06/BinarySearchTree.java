package tp06;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class BinarySearchTree<K extends Comparable<K>,V extends Comparable<V>> implements Map<K,V> {

	K key;
	V value;

	BinarySearchTree<K, V> left;
	BinarySearchTree<K, V> right;

	String txt;
	int size;

	public BinarySearchTree(K key, V value) {
		this.key = key;
		this.value = value;
		left = null;
		right = null;
	}

	public BinarySearchTree(){
		this.left = new BinarySearchTree<>();
		this.right = new BinarySearchTree<>();
	}

	public BinarySearchTree<K, V> getSuccessor(BinarySearchTree<K, V> deleleNode){
		BinarySearchTree<K, V> successsor = null;
		BinarySearchTree<K, V> successsorParent = null;
		BinarySearchTree<K, V> current = deleleNode.right;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		if(successsor!=deleleNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}
	public String display(BinarySearchTree<K, V> bst){
		if(bst!=null){
			display(bst.left);
			txt += " " + bst.value + "\n"; 
			display(bst.right);
		}
		return txt;
	}

	public String toString() {
		txt = "";
		return display(this);
	}

	public static void main(String arg[]){
		BinarySearchTree<Integer, Integer> b = new BinarySearchTree<Integer,Integer>(25, 5);
		b.put(4, 12);
		b.put(6, 6);
		b.put(3, 16);
		b.put(10, 9);
		b.put(15, 4);
		b.put(8, 2);
		b.put(10, 6);
		b.put(13, 8);

		System.out.println("Original Tree : ");
		System.out.println(b.toString());		
		System.out.println("La plus petite valeur est :" + b.bstMin());
		System.out.println("La valeur 4 existe ? : " + b.containsValue(4));
		System.out.println("La clé 4 existe ? : " + b.containsKey(4));
		System.out.println("On supprime la clé 6 : " + b.remove(6));
		System.out.println(b.toString());		
		System.out.println("On supprime la clé 8 avec enfants " + b.remove(8));		
		System.out.println(b.toString());		
		System.out.println("On supprime la clé 10 sans enfants " + b.remove(10));		
		System.out.println(b.toString());		

	}

	public V bstMin() {
		if(this.left == null) {
			return this.value;
		}
		System.out.println(value + " " + this.left.value);
		return this.left.bstMin();
	}

	public void deleteMin() {
		if(this.left == null) {
			this.value = null;
			this.key = null;
		}
		else this.left.deleteMin();
	}

	@Override
	public int size() {
		if(this.isEmpty()) return 0;
		return size(this);
	}

	public int size(BinarySearchTree<K, V> bst) {
		if(bst==null){
			return 0;
		}
		return 1 + size(bst.left) + size(bst.right);
	}

	@Override
	public boolean isEmpty() {
		return get(this.key) == null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean containsKey(Object key) {
		if(this.key.equals( key)){
			return true;
		}
		if(((Comparable<K>) key).compareTo(this.key) < 0){
			return left == null ? null : left.containsKey(key);
		} else if(((Comparable<K>) key).compareTo(this.key) > 0){
			return right == null ? null : right.containsKey(key);
		} else {
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Override
	public boolean containsValue(Object value) {
		if(this.value.equals(value)){
			return true;
		}
		if((this.value).compareTo((V) value) > 0){
			return get(left) == null ? null : left.containsValue(value);
		} else if((this.value).compareTo((V) value) < 0) {
			return get(right) == null ? null : right.containsValue(value);
		} else {
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Override
	public V get(Object key) {
		if(this.key.equals( key)){
			return value;
		}
		if(this.key.compareTo((K) key) > 0){
			return get(left) == null ? null : left.get( key );
		} else {
			return get(right) == null ? null : right.get( key );
		}
	}

	@Override
	public V put(K key, V value) {
		if(this.key.compareTo(key) < 0){          
			if ( left != null ) {                 
				left.put(key, value);             
			} else {                 
				left = new BinarySearchTree<K,V>(key,value);
				return value;
			} 
		}         
		else if(this.key.compareTo(key) > 0){
			if ( right != null ) {
				right.put(key, value);
			}
			else {
				right = new BinarySearchTree<K,V>(key,value);
				return value;
			}
		} else {
			this.key = key;
			return value;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {

		BinarySearchTree<K, V> parent = this;
		BinarySearchTree<K, V> current = this;
		BinarySearchTree<K, V> root = this;

		boolean isLeftChild = false;
		V val = this.value;

		while(current.key!=key){
			parent = current;
			val = current.value;
			if((this.key).compareTo((K) key) < 0){
				isLeftChild = true;
				current = current.left;
			}else{
				isLeftChild = false;
				current = current.right;
			}
			if(current == null){
				return null;
			}
		}
		if(get(current.left) == null && get(current.right) == null) {
			val = current.value;
			if(current == root){
				current = null;
			}
			if(isLeftChild == true){
				parent.left = null;
			}else{
				parent.right = null;
			}
			return val;
		}
		else if(current.right == null){
			val = current.value;
			if(current == this){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			val = current.value;
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			val = current.value;
			BinarySearchTree<K, V> successor = getSuccessor(current);
			if(current==this){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return val;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
}
