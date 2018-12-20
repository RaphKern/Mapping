package robot_parcours;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	public Robot control;
	
	public Panel(Robot ctrl) {
		this.control = ctrl;
	}
	
	public void paint(Graphics g) {
		int width = this.control.carteAExplorer.largeur;
		int height = this.control.carteAExplorer.hauteur;
		int tc=this.control.tailleCase;
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				// Dessin de la carte à explorer
				int c1Value = this.control.carteAExplorer.carte[i][j];
				g.setColor(valToColor(c1Value));
				g.fillRect(10+j*tc, 10+i*tc, tc, tc);
				// Dessin de la carte en construction
				int c2Value = this.control.carteEnConstruction.carte[i][j];
				g.setColor(valToColor(c2Value));
				g.fillRect(20+this.control.carteAExplorer.largeur*tc+j*tc, 10+i*tc, tc, tc);
			}
		}
	}
	
	public Color valToColor(int k) {
		if(k==-1) {
			return new Color(0, 255, 255);
		}
		else if(k==0) {
			return new Color(255, 255, 255);
		}
		else if(k==1) {
			return new Color(0, 0, 0);
		}
		else if(k==2) {
			return new Color(0, 0, 128);
		}
		else if(k==3) {
			return new Color(255, 0, 0);
		}
		else if(k==4) {
			return new Color(128, 128, 128);
		}
		return new Color(255,255,255);
	}

}
