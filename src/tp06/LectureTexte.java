package tp06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LectureTexte {

	public static void main(String args[]) {
		Map<String, Integer> map = occurences();
		String[][] tab  = motFrequents(map);
		for(int i = 0; i < 5; i++) {
			System.out.println(tab[i][0] + " : occurence : " + tab[i][1]);
		}
		/*for (String mot: map.keySet()){
			String key = mot.toString();
			String value = map.get(mot).toString();  
			System.out.println(key + " : occurence : " + value);  
		} */
	}

	public static Map<String, Integer> occurences() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		try{
			InputStream flux=new FileInputStream("ressources/NotreDameDeParis.txt"); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			while ((ligne=buff.readLine())!=null){
				String[] tab = ligne.split(" ");
				for(String mot : tab) {
					if(map.containsKey(mot)) map.put(mot, map.get(mot)+1);
					else map.put(mot, 1);
				}

			}
			buff.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return map;
	}

	public static String[][] motFrequents(Map<String, Integer> map){
		String[][] tab = new String[][] {{"0","0"},{"0","0"},{"0","0"},{"0","0"},{"0","0"}};
		for(int i = 0; i<tab.length ; i++) {
			for(String mot : map.keySet()) {
				if(Integer.valueOf(tab[i][1]) < Integer.valueOf(map.get(mot).toString())) {
					if(i==0) {
						tab[i][0] = mot;
						tab[i][1] = map.get(mot).toString();
					} else if(Integer.valueOf(tab[i-1][1]) > Integer.valueOf(map.get(mot).toString())) {
						tab[i][0] = mot;
						tab[i][1] = map.get(mot).toString();
					}
				}
			}
		}
		return tab;
	}
}