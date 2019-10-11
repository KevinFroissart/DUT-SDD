package tp5;

import java.util.Map.Entry;

public class HashCouple implements Entry<String, Integer> {
	
	protected String key;
	protected Integer value;
	
	public HashCouple(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public Integer setValue(Integer value) {
		Integer tmp = this.value;
		this.value = value;
		return tmp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashCouple other = (HashCouple) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "[" + key + "," + value + "]";
	}

}
