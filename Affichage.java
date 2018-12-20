package robot_parcours;

public class Affichage {

	public static void main(String[] args) {
		//int[] colonnes = new int[] {};
		//int[] lignes = new int[] {};
		//Map m=new Map(20,20,lignes,colonnes);
		Map m=new Map(20,20,0.85);
		Robot Robert=new Robot(m, 1, 1, 30);
	}

}
