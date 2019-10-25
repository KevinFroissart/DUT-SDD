package tp03;

import java.util.Stack;

public class Parcours {

	public static void main(String[] args) {
		Labyrinthe lab = new Labyrinthe();
		Stack<Cellule>pile = new Stack<>();
		Cellule pos = new Cellule(0, 1);
		Cellule arrivee = new Cellule(40, 39);
		int cptChemin = 0;

		pile.add(pos);
		lab.poserMarque(pos.getX(), pos.getY());

		try {
			while(!pile.isEmpty() && !pos.equals(arrivee)) {
				pos = pile.peek();
				if(!lab.estMur(pos.getX()+1, pos.getY()) && !lab.estMarque(pos.getX()+1, pos.getY())) {
					pos = new Cellule(pos.getX()+1, pos.getY());
					pile.add(pos);

					lab.poserMarque(pos.getX(), pos.getY());
					cptChemin++;

				}else if(!lab.estMur(pos.getX()-1, pos.getY()) && !lab.estMarque(pos.getX()-1, pos.getY())) {
					pos = new Cellule(pos.getX()-1, pos.getY());
					pile.add(pos);
					lab.poserMarque(pos.getX(), pos.getY());
					cptChemin++;

				}else if(!lab.estMur(pos.getX(), pos.getY()+1) && !lab.estMarque(pos.getX(), pos.getY()+1)) {
					pos = new Cellule(pos.getX(), pos.getY()+1);
					pile.add(pos);
					lab.poserMarque(pos.getX(), pos.getY());
					cptChemin++;

				}else if(!lab.estMur(pos.getX(), pos.getY()-1) && !lab.estMarque(pos.getX(), pos.getY()-1)) {
					pos = new Cellule(pos.getX(), pos.getY()-1);
					pile.add(pos);
					lab.poserMarque(pos.getX(), pos.getY());
					cptChemin++;
				}else {
					lab.poserMarqueRetour(pos.getX(), pos.getY());
					pile.pop();
					cptChemin--;
				}
				Thread.sleep(5);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(StringIndexOutOfBoundsException e1) {
			System.out.println("Aucune sortie trouvée !");
		}
		System.out.println("Sortie trouvée");
		System.out.println("Taille chemin rouge : " + cptChemin);
	}
}