package tp06;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class BinarySearchTree<K,V> implements Map<K,V> {

	public static Node root;

	K key;
	V value;

	BinarySearchTree<K, V> left;
	BinarySearchTree<K, V> right;

	public BinarySearchTree(K key, V value) {
		this.key = key;
		this.value = value;
	}
	public BinarySearchTree(){
		root = null;
	}

	public boolean find(int id){
		Node current = root;
		while(current!=null){
			if(current.data==id){
				return true;
			}else if(current.data>id){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}
	public boolean delete(int id){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(current.data!=id){
			parent = current;
			if(current.data>id){
				isLeftChild = true;
				current = current.left;
			}else{
				isLeftChild = false;
				current = current.right;
			}
			if(current ==null){
				return false;
			}
		}
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}
			if(isLeftChild ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		else if(current.right==null){
			if(current==root){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			Node successor	 = getSuccessor(current);
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}

	public Node getSuccessor(Node deleleNode){
		Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.right;
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
	public void display(Node root){
		if(root!=null){
			display(root.left);
			System.out.print(" " + root.data);
			display(root.right);
		}
	}

	public static void main(String arg[]){
		BinarySearchTree b = new BinarySearchTree();

		System.out.println("Original Tree : ");
		b.display(b.root);		
		System.out.println("");
		System.out.println("Check whether Node with value 4 exists : " + b.find(4));
		System.out.println("Delete Node with no children (2) : " + b.delete(2));		
		b.display(root);
		System.out.println("\n Delete Node with one child (4) : " + b.delete(4));		
		b.display(root);
		System.out.println("\n Delete Node with Two children (10) : " + b.delete(10));		
		b.display(root);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		if(this.key.equals( key)){
			return true;
		}
		if(((String) key).compareTo((String) this.key) < 0){
			return left == null ? null : left.containsKey(key);
		} else if(((String) key).compareTo((String) this.key) > 0){
			return right == null ? null : right.containsKey(key);
		} else {
			return false;
		}
	}

	@Override
	public boolean containsValue(Object value) {
		if(this.value.equals(value)){
			return true;
		}
		if(((String) value).compareTo((String) this.value) < 0){
			return left == null ? null : left.containsValue(value);
		} else if(((String) key).compareTo((String) this.value) > 0){
			return right == null ? null : right.containsValue(value);
		} else {
			return false;
		}
	}

	@Override
	public V get(Object key) {
		if(this.key.equals( key)){
			return value;
		}
		if(((String) key).compareTo((String) this.key) < 0){
			return left == null ? null : left.get( key );
		} else {
			return right == null ? null : right.get( key );
		}
	}

	@Override
	public V put(K key, V value) {
		if(((String) key).compareTo((String) this.key) < 0 ) {             
			if ( left != null ) {                 
				left.put(key, value);             
			} else {                 
				left = new BinarySearchTree<K,V>(key,value);             
			}         
		}         
		else if(((String) key).compareTo((String) this.key) > 0 )
		{
			if ( right != null ) {
				right.put(key, value);
			}
			else {
				right = new BinarySearchTree<K,V>(key,value);
			}
		} else {
			this.value = value;
		}
		return null;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
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

class Node{
	int data;
	Node left;
	Node right;	
	public Node(int data){
		this.data = data;
		left = null;
		right = null;
	}
}