package robot_parcours;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class Robot {
	public int i;         /* position ligne dans la matrice*/ 
	public int j;		  /* position colonne dans la matrice*/
	public Map carteAExplorer;
	public Map carteEnConstruction;
	public Window window;
	public int tailleCase;
	public int dir = 1;
	public int ntour = 0;
	public ArrayList<Integer> instructions = new ArrayList<Integer>();
	
	public Robot (Map m, int ii, int jj, int taille){
		this.i=ii;
		this.j=jj;
		this.carteAExplorer=m;
		this.carteEnConstruction=new Map(carteAExplorer.hauteur, carteAExplorer.largeur, ii, jj);
		this.carteEnConstruction.actualiseCarte(m, ii, jj);
		this.window= new Window(this, taille);
		this.tailleCase=taille;
	}
	
	
	
	public void doAction(KeyEvent e){
		if(this.carteEnConstruction.isExplored()==true){
			this.carteEnConstruction.carte[this.getI()][this.getJ()]=0;
			if(this.carteEnConstruction.memeCarte(this.carteAExplorer)) {
				JOptionPane.showMessageDialog(this.window, "La carte reproduite est exacte !");
			}
			else {
				JOptionPane.showMessageDialog(this.window, "Il existe des erreurs.");
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			this.up();
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			this.right();
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			this.down();
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			this.left();
		}
		else if(e.getKeyCode()==KeyEvent.VK_N){
			this.nextAutomaticStep();
		}
		else if(e.getKeyCode()==KeyEvent.VK_A){
			this.automaticGame();
		}
		else if(e.getKeyCode()==KeyEvent.VK_E){
			if(dir==1) {
				if(instructions.size()==0) {
					instructions.add(1);
					instructions.add(1);
				}
				if(((this.j)+1 != this.carteAExplorer.largeur-1-(ntour*2))||((this.i)-1 != (ntour)*2)) {
					this.move(instructions, dir);
				}
				else {
					instructions.clear();
					dir=2;
				}
			}
			else if(dir==2) {
				if(instructions.size()==0) {
					instructions.add(2);
					instructions.add(2);
				}
				if(((this.i)+1 != this.carteAExplorer.hauteur-1-(ntour*2))||((this.j)+1 != this.carteAExplorer.largeur-1-(ntour*2))) {
					this.move(instructions, dir);
				}
				else {
					instructions.clear();
					dir=3;
				}
			}
			else if(dir==3) {
				if(instructions.size()==0) {
					instructions.add(3);
					instructions.add(3);
				}
				if(((this.j)-1 != ntour*2)||(this.i)+1 != this.carteAExplorer.hauteur-1-(ntour*2)) {
					this.move(instructions, dir);
				}
				else {
					instructions.clear();
					dir=0;
				}
			}
			else if(dir==0) {
				if(instructions.size()==0) {
					instructions.add(0);
					instructions.add(0);
				}
				if(((this.i)-1 != (ntour+1)*2)||((this.j)-1 != ntour*2)) {
					this.move(instructions, dir);
				}
				else {
					instructions.clear();
					dir=1;
					ntour=ntour+1;
				}
			}
		}
	}
	
	public void right() {
		if(this.carteEnConstruction.carte[this.i][this.j+1]==0) {
			this.setJ(this.j+1);
			this.carteEnConstruction.carte[this.i][this.j]=3;
			this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
		}
	}
	
	public void down() {
		if(this.carteEnConstruction.carte[this.i+1][this.j]==0) {
			this.setI(this.i+1);
			this.carteEnConstruction.carte[this.i][this.j]=3;
			this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
		}
	}
	
	public void left() {
		if(this.carteEnConstruction.carte[this.i][this.j-1]==0) {
			this.setJ(this.j-1);
			this.carteEnConstruction.carte[this.i][this.j]=3;
			this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
		}
	}
	
	public void up() {
		if(this.carteEnConstruction.carte[this.i-1][this.j]==0) {
			this.setI(this.i-1);
			this.carteEnConstruction.carte[this.i][this.j]=3;
			this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
		}
	}
	
	public void nextAutomaticStep() {
		ArrayList<Integer> l= casesAutour(this.carteEnConstruction, this.i, this.j);
		int direction=choixDirection(l);
		if(direction==3) {
			this.setJ(this.j-1);
			this.carteEnConstruction.carte[this.i][this.j]=3;
			this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
		}
		if(direction==0) {
			this.setI(this.i-1);
			this.carteEnConstruction.carte[this.i][this.j]=3;
			this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
		}
		if(direction==1) {
			this.setJ(this.j+1);
			this.carteEnConstruction.carte[this.i][this.j]=3;
			this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
		}
		if(direction==2){
			this.setI(this.i+1);
			this.carteEnConstruction.carte[this.i][this.j]=3;
			this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
		}
	}

	public ArrayList<Integer> casesAutour(Map m, int i, int j){
		ArrayList<Integer> l= new ArrayList<Integer>();
		l.add(m.carte[i-1][j]);
		l.add(m.carte[i][j+1]);
		l.add(m.carte[i+1][j]);
		l.add(m.carte[i][j-1]);
		return l;
	}
	
	public int choixDirection(ArrayList<Integer> liste) {
		ArrayList<Integer> choixPossibles= new ArrayList<Integer>();
		for(int ii=0; ii<liste.size(); ii++) {
			if(liste.get(ii)==0) {
				choixPossibles.add(ii);
			}
		}
		int indice = (int)(Math.random()*choixPossibles.size());
		return choixPossibles.get(indice);
	}
	
	public void automaticGame(){
		while (this.carteEnConstruction.isExplored()==false){
			nextAutomaticStep();
		}
	}
	
	public void move(ArrayList<Integer> instructions, int dir) {
		if(instructions.get(0)==0) {
			if(this.carteEnConstruction.carte[this.i-1][this.j]==0) {
				this.setI(this.i-1);
				this.carteEnConstruction.carte[this.i][this.j]=3;
				this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
				if(instructions.size()==2) {
					instructions.add(dir);
				}
				instructions.remove(0);
			}
			else {
				eviteObstacle(instructions);
			}
		}
		else if(instructions.get(0)==1) {
			if(this.carteEnConstruction.carte[this.i][this.j+1]==0) {
				this.setJ(this.j+1);
				this.carteEnConstruction.carte[this.i][this.j]=3;
				this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
				if(instructions.size()==2) {
					instructions.add(dir);
				}
				instructions.remove(0);
			}
			else {
				eviteObstacle(instructions);
			}
		}
		else if(instructions.get(0)==2) {
			if(this.carteEnConstruction.carte[this.i+1][this.j]==0) {
				this.setI(this.i+1);
				this.carteEnConstruction.carte[this.i][this.j]=3;
				this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
				if(instructions.size()==2) {
					instructions.add(dir);
				}
				instructions.remove(0);
			}
			else {
				eviteObstacle(instructions);
			}
		}
		else if(instructions.get(0)==3) {
			if(this.carteEnConstruction.carte[this.i][this.j-1]==0) {
				this.setJ(this.j-1);
				this.carteEnConstruction.carte[this.i][this.j]=3;
				this.carteEnConstruction.actualiseCarte(this.carteAExplorer, this.i, this.j);
				if(instructions.size()==2) {
					instructions.add(dir);
				}
				instructions.remove(0);
			}
			else {
				eviteObstacle(instructions);
			}
		}
	}
	
	public void eviteObstacle(ArrayList<Integer> instructions) {
		if(instructions.get(0)==instructions.get(1)) {
			instructions.add(0,(instructions.get(1)+1)%4);
			instructions.add(3,(instructions.get(1)+3)%4);
		}
		else if(instructions.get(0)==(instructions.get(1)+1)%4){
			instructions.add(0,(instructions.get(0)+1)%4);
			instructions.add(2,instructions.get(1));
			instructions.add(4,instructions.get(3));
			instructions.add(5,(instructions.get(3)+3)%4);
		}
		else {
			instructions.add(0,instructions.get(1));
			instructions.remove(2);
		}
	}
	
	public int getI() {
		return this.i;
	}

	public void setI(int ii) {
		this.i = ii;
	}

	public int getJ() {
		return this.j;
	}

	public void setJ(int jj) {
		this.j = jj;
	}

}
