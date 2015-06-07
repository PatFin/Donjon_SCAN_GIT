package fr.donjon.cases;

/**
 * CaseEscalier
 * Il s'agit d'une case sans comportements spéciaux.
 * Elle prends l'apparence de marches horizontales successive.
 * @author Patrick
 *
 */
public class CaseEscalier extends Case {
	
	final static String image="Case_escalier.png";
	
	/**
	 * Constructeur de la classe Case_Escalier
	 * Pas de comportement spécifiques
	 */
	public CaseEscalier() {
		super(image);
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.cases.Case#clone()
	 */
	@Override
	public Case clone() {
		return new CaseEscalier();
	}
	
}
