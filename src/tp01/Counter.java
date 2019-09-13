package tp01;

public class Counter {

	private static int count;
	private static int perm;

	public void incComp(){
		count++;
	}
	public void incComp(int n){
		count+=n;
	}

	public void incPerm(){
		perm++;
	}

	public void incPerm(int i){
		perm+=i;
	}
	
	public void reset() {
		count = 0;
		perm = 0;
	}
	
	public String toString() {
		return "Nombre de comparaisons : " + count + " , Nombre de permutations : " + perm;
	}
	
	

}
