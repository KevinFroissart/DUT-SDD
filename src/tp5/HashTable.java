package tp5;


public interface HashTable {
	
	public Integer put(String key, Integer value);
	public Integer get(String key);
	public boolean remove(String key);
	public boolean contains(String key);
	public int size();
}