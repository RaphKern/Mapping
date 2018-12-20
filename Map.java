package robot_parcours;

public class Map {
	public int largeur;
	public int hauteur;
	public int[][] carte;
	
	public Map(int h, int l){
		this.largeur=l;
		this.hauteur=h;
		this.carte=new int[h][l];
		for(int i=0; i<h; i++) {
			for(int j=0; j<l; j++) {
				if(i==0 || j==0 || i==h-1 || j==l-1) {
					this.carte[i][j]=4;
				}
				else {
					this.carte[i][j]=-1;
				}
			}
		}
	}
	
	public Map(int h, int l, int[] listeAbscisses, int[] listeOrdonnees){
		this.largeur=l;
		this.hauteur=h;
		this.carte=new int[h][l];
		for(int i=0; i<h; i++) {
			for(int j=0; j<l; j++) {
				if(i==0 || j==0 || i==h-1 || j==l-1) {
					this.carte[i][j]=4;
				}
				else {
					this.carte[i][j]=0;
				}
			}
		}
		for(int i=0; i<listeAbscisses.length; i++) {
			this.carte[listeAbscisses[i]][listeOrdonnees[i]]=1;
		}
	}
	
	public Map(int h, int l, int ii, int jj){
		this.largeur=l;
		this.hauteur=h;
		this.carte=new int[h][l];
		for(int i=0; i<h; i++) {
			for(int j=0; j<l; j++) {
				if(i==0 || j==0 || i==h-1 || j==l-1) {
					this.carte[i][j]=4;
				}
				else {
					this.carte[i][j]=-1;
				}
			}
		}
		this.carte[ii][jj]=3;
	}

	public Map(int h, int l, double p){
		this.largeur=l;
		this.hauteur=h;
		this.carte=new int[h][l];
		double rnd;
		for(int i=0; i<h; i++) {
			for(int j=0; j<l; j++) {
				if(i==0 || j==0 || i==h-1 || j==l-1) {
					this.carte[i][j]=4;
				}
				else {
					rnd=Math.random();
					if (rnd<p) {
						this.carte[i][j]=0;
					}
					else {
						this.carte[i][j]=1;
					}
				}
			}
		}
	}
	
	
	public int getLargeurCarte() {
		return this.largeur;
	}

	public void setLargeurCarte(int l) {
		this.largeur = l;
	}

	public int getHauteurCarte() {
		return this.hauteur;
	}

	public void setHauteurCarte(int h) {
		this.hauteur = h;
	}

	public int[][] getCarte() {
		return this.carte;
	}

	public void setCarte(int[][] matrice) {
		this.carte = matrice;
	}

	public void actualiseCarte(Map carteAExplorer,int i,int j) {
		this.carte[i+1][j]=carteAExplorer.carte[i+1][j];
		this.carte[i-1][j]=carteAExplorer.carte[i-1][j];
		this.carte[i][j+1]=carteAExplorer.carte[i][j+1];
		this.carte[i][j-1]=carteAExplorer.carte[i][j-1];
	}
	
	public void printCarte() {
		String str="";
		for(int i=0;i<this.hauteur;i++) {
			str="";
			for(int j=0;j<this.largeur;j++) {
				str=str+this.carte[i][j]+" ";
			}
			System.out.println(str);
		}
	}
	
	public boolean isExplored() {
		for(int i=1;i<this.hauteur-1;i++) {
			for(int j=1;j<this.largeur-1;j++) {
				if(this.carte[i][j]==-1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean memeCarte(Map m) {
		for(int i=0; i<this.hauteur; i++) {
			for(int j=0; j<this.largeur; j++) {
				if(this.carte[i][j]!=m.carte[i][j]){
					return(false);
				}
			}
		}
		return(true);
	}
	
}
