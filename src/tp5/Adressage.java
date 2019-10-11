package tp5;

public class Adressage implements HashTable {
	
	public static final int DEFAULT_SIZE = 16;
	private HashCouple[] table;
	private int cpt;

	public Adressage(int taille) {
		table = new HashCouple[taille];
		this.cpt = 0;
	}

	public Adressage() {
		this(DEFAULT_SIZE);
	}
	
	public static int modulo(int a, int b) {
		int tmp=a%b;
		if(tmp<0) tmp+=b;
		return tmp;
	}
	
	private int nextEmpty(int i) {
		int j=0;
		while(table[i]!=null && j<table.length) {
			if(j==table.length-1) {
				i=0;
			}
			i++;
			j++;
		}
		return i;
	}

	@Override
	public Integer put(String key, Integer value) {
		int i = modulo(key.hashCode(), table.length);
		i = nextEmpty(i);
		table[i]= new HashCouple(key, value);
		this.cpt++;
		return null;
	}

	@Override
	public boolean remove(String key) {
		if (this.contains(key)) {
			int i = modulo(key.hashCode(), table.length);
			table[i] = null;
			this.cpt--;
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(String key) {
		for (HashCouple h : table) {
			if (h.getKey().equals(key)) return true;
		}
		return false;
	}

	@Override
	public int size() {
		return cpt;
	}

	@Override
	public Integer get(String key) {
		for (HashCouple h : table) {
			if (h.getKey().equals(key)) return h.getValue();
		}
		return null;
	}
}