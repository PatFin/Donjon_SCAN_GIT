/**
 * 
 */
package fr.donjon.utils;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import fr.donjon.classes.Personnage;

/**
 * 
 * Dermet de décrire des effet appliquables a un personnage et modifiant ses stats.
 * 
 * @author Baptiste
 *
 */
public class Effet{

	Personnage p;
	
	public int vie;		//Bonus/Malus de	Vie
	public int def;		//					Defense
	public int vit;		//					Vitesse
	public int atk;		//					Attaque

	int duree;			//Durée de l'effet

	boolean actif;		//Définit si l'effet est actif
	boolean cummulable;	//Définit si l'effet doit etre cummulé
	boolean timed;		//Définit si l'effet est temporisé
	boolean ignoreEtat;	//Définit si l'effet doit ajouté aux autres selon son activité ou non

	Timer t;			//Timer associé a la temporisation	

	Drawable drawable;
	
	/**
	 * 
	 * Crée un effet non temporisé, non cummulable avec comportement selon activité
	 * 
	 * @param eVie
	 * @param eDef
	 * @param eVit
	 * @param atk
	 */
	public Effet(Personnage p,int eVie, int eDef, int eVit, int atk){
		this(p,eVie, eDef, eVit, atk, false);

	}

	/**
	 * Crée un effet non temporisé.
	 * 
	 * @param eVie
	 * @param eDef
	 * @param eVit
	 * @param atk
	 * @param cummulable
	 * @param iEtat
	 */
	public Effet( Personnage p,int eVie, int eDef, int eVit, int atk, boolean cummulable, boolean iEtat ){
		this(p,eVie, eDef, eVit, atk, -1, cummulable, iEtat);
	}

	/**
	 * 
	 * Crée un effet non temporisé avec comportement selon activité.
	 * 
	 * @param eVie
	 * @param eDef
	 * @param eVit
	 * @param atk
	 * @param cummulable
	 */
	public Effet( Personnage p,int eVie, int eDef, int eVit, int atk, boolean cummulable ){
		this(p,eVie, eDef, eVit, atk, cummulable, false);
	}

	/**
	 * 
	 * Crée un effet temporisé avec comportement selon activité.
	 * 
	 * @param eVie
	 * @param eDef
	 * @param eVit
	 * @param atk
	 * @param eDur
	 * @param cummulable
	 */
	public Effet( Personnage p,int eVie, int eDef, int eVit, int atk, int eDur, boolean cummulable) {
		this(p,eVie, eDef, eVit, atk, eDur, cummulable, false);
	}

	/**
	 * Crée un effet entièrement personnalisé
	 * 
	 * @param eVie
	 * @param eDef
	 * @param eVit
	 * @param atk
	 * @param eDur
	 * @param cummulable
	 * @param iEtat
	 */
	public Effet( Personnage p, int eVie, int eDef, int eVit, int atk, int eDur, boolean cummulable, boolean iEtat) {

		this.p = p;
		
		this.vie = eVie;
		this.def = eDef;
		this.vit = eVit;
		this.atk = atk;

		this.duree = eDur;

		this.cummulable = cummulable;
		this.ignoreEtat = iEtat;

		this.timed = eDur <= 0 ? false : true;
		this.actif = true;

		if(timed){
			t = new Timer(duree, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//Désactive l'effet à la fin de la temporisation
					actif = false;
					t.stop();
				}
			});

			t.start();
		}
	}

	
	
	/**
	 * Renvoie la somme avec un autre effet sans modifier l'objet actuel,
	 * La vitesse et l'attaque valerons zéro si les valeurs calculées sont négatives
	 * 
	 * @param e	L'effet a ajouter
	 * @return La somme des effets
	 */
	public Effet sum(Effet e){

		return new Effet(e.p, vie+ e.vie  ,
							def+ e.def ,
							vit+ e.vit >= 0 ? vit+ e.vit : 0, //Speed should not be negative
							atk+ e.atk >= 0 ? atk+ e.atk : 0);//Attak should not be negative
	}

	/**
	 * 
	 * Permet d'ajouter les paramètres d'un effet à l'effet courrant,
	 * cette méthode est utile lors de l'utilisation de sum(Effet e) car elle supprime
	 * des données lors de l'ajout (timed, cummulable,etc...)
	 * 
	 * 
	 * @param e
	 * @return
	 */
	public Effet initialiseWithParametersOf(Effet e){
		
		return new Effet(e.p,vie, def, vit, atk, e.duree, e.cummulable, e.ignoreEtat);
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Effet [vie=" + vie + ", def=" + def + ", vit=" + vit + ", atk="
				+ atk + ", actif=" + actif + ", cummulable=" + cummulable
				+ ", timed=" + timed + ", ignoreEtat=" + ignoreEtat + "]";
	}

	
	
	public void draw(Graphics g, long t) {
		if(drawable!=null && actif){
			drawable.draw(p, g, t);
		}
	}
	
	public void setDrawable(Drawable d){
		this.drawable = d;
	}
	

	
	

	



}
