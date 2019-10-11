package tp5;

import java.util.LinkedList;

public class Chainage implements HashTable {
	
	protected LinkedList<HashCouple>[] table;
	public static final int DEFAULT_SIZE =16;
	protected int cpt;
	
	@SuppressWarnings("unchecked")
	public Chainage(int taille) {
		this.table = (LinkedList<HashCouple>[])new LinkedList[taille];
		for (int i=0; i<table.length; i++) {
			table[i] = new LinkedList<HashCouple>();
		}
		this.cpt = 0;
	}
	
	public Chainage() {
		this(DEFAULT_SIZE);
	}
	
	@Override
	public String toString() {
		String res = "[";
		for (int i=0; i<table.length; i++) {
			for (HashCouple h : table[i]) {
				res += "," + h.toString();
			}
		}
		res=res.replaceFirst(",","");
		return res + "]";
	}

	@Override
	public int size() {
		return cpt;
	}

	@Override
	public boolean contains(String key) {
		int i = modulo(key.hashCode(), table.length);
		LinkedList<HashCouple> list = table[i];
		HashCouple hc = findKey(list, key);
		return !(hc==null);
	}

	public static int modulo(int a, int b) {
		int tmp=a%b;
		if(tmp<0) tmp+=b;
		return tmp;
	}

	private HashCouple findKey(LinkedList<HashCouple> l, String key) {
		for(HashCouple i : l) {
			if(i.getKey().equals(key)) return i;
		}
		return null;
	}
	
	@Override
	public Integer get(String key) {
		if (this.contains(key)) {
			int i = modulo(key.hashCode(), table.length);
			LinkedList<HashCouple> list = table[i];
			return findKey(list, key).getValue();
		}
		return null;
	}

	@Override
	public Integer put(String key, Integer value) {
		int i = modulo(key.hashCode(), table.length);
		LinkedList<HashCouple> list = table[i];
		HashCouple hc = findKey(list, key);
		if(hc==null) {
			list.add(new HashCouple(key, value));
			this.cpt++;
			return null;
		} else return hc.setValue(value);
	}

	@Override
	public boolean remove(String key) {
		if(contains(key)) {
			int i = modulo(key.hashCode(), table.length);
			LinkedList<HashCouple> list = table[i];
			HashCouple hC = findKey(list, key);
			list.remove(hC);
			this.cpt--;
			return true;
		}
		return false;
	}

}
