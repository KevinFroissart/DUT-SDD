package tp02;

public class MyList<E> {

	E element;
	MyList<E> previous;
	MyList<E> next;

	public MyList(E element, MyList<E> previous, MyList<E> next){
		this.element = element;
		this.previous = previous;
		this.next = next;
	}

	public MyList(){
		this.element = null;
		this.previous = null;
		this.next = null;
	}

	public boolean isEmpty() {
		return element == null;
	}

	public int size() {
		int cpt = 0;
		while(!next.isEmpty()) cpt++;
		return cpt;
	}

	public void clear() {
		while(!next.isEmpty()) {
			element = null;
		}
	}

	public String toString() {
		String res = "[";
		while(!next.isEmpty()) {
			res += element + ",";
		}
		return res + "]";
	}

	public E get(int index) {
		E res = null;
		for(int i=0; i<index && !next.isEmpty(); i++) {
			res = element;
		}
		return res;
	}

	public int indexOf(Object o) {
		for(int i=0; !next.isEmpty(); i++) {
			if(element.equals(o)) return i;
		}
		return 0;
	}

	boolean contains(Object o) {
		boolean res = true;
		while(!next.isEmpty()) {
			if(element.equals(o)) res = true;
		}
		return res;
	}
	
	public int lastIndexOf(Object o) {
		int idx = 0;
		for(int i=0; !next.isEmpty(); i++) {
			if(element.equals(o)) idx = i;
		}
		return idx;
	}
	
	public boolean add(E element) {
		while(!next.isEmpty()) {
			
		}
		next.element = element;
		return true;
	}
	
	public void add(int index, E element) {
		for(int i=0; !next.isEmpty(); i++) {
			if(i == index) this.element = element;
		}
	}
	
	public E remove(int index) {
		E res = null;
		for(int i=0; !next.isEmpty(); i++) {
			if(i == index) {
				res = this.element;
				this.element = null;
			}
		}
		return res;
	}
	
	public boolean remove(Object o) {
		boolean res = false;
		for(int i=0; !next.isEmpty(); i++) {
			if(this.element.equals(o)) {
				res = true;
				this.element = null;
			}
		}
		return res;
	}
}
