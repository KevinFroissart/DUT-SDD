package tp5;

import java.util.Map;

public class HashCouple implements Map.Entry<String,Integer>{
	
	String cle;
	int value;
	
	public HashCouple(String cle, int information) {
		this.cle=cle;
		value = information;
	}

	@Override
	public String getKey() {
		return cle;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Integer setValue(Integer arg0) {
		int tmp = value;
		this.value = arg0;
		return tmp;
	}

}
