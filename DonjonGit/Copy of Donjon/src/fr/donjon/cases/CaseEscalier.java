package fr.donjon.cases;

public class CaseEscalier extends Case {
	
	final static String image="Case_escalier.png";
	
	/**
	 * Constructeur de la classe Case_Escalier
	 * Il s'agit d'une case sans comportements spéciaux.
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
