package robot_parcours;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	public Panel panel;
	public Robot control;
	public int tailleCase;
	
	public Window(Robot ctrl, int taille) {
		this.control = ctrl;
		this.tailleCase = taille;
		JFrame wdw = new JFrame();
		this.panel = new Panel(this.control);
		wdw.setTitle("Cartographie 2D");
		int largeur = (this.control.carteAExplorer.largeur*2*taille)+45;
		int hauteur = (this.control.carteAExplorer.hauteur*taille)+60;
		wdw.setPreferredSize(new Dimension(largeur,hauteur));
		wdw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wdw.setBackground(Color.WHITE);
		wdw.add(this.panel);
		wdw.addKeyListener(this);
		wdw.pack();
		wdw.setLocationRelativeTo(null);
		wdw.setVisible(true);
	}
	
	public void keyPressed(KeyEvent e) {
		this.control.doAction(e);
		this.panel.repaint();
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	

}
