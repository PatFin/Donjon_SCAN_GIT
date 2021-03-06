/**
 * 
 */
package fr.donjon.utils;

import java.util.ArrayList;
import fr.donjon.cases.Case;

/**
 * @author Baptiste
 *
 */
public class ProbabilityMap {


	ArrayList<CaseProbability> probs;

	float total;
	/**
	 * 
	 */
	public ProbabilityMap() {
		probs = new ArrayList<CaseProbability>();
	}

	private void sortArray(){

		/*probs.sort(new Comparator<CaseProbability>() {

			@Override
			public int compare(CaseProbability o1, CaseProbability o2) {

				return  Double.compare(o1.proba, o2.proba);
			}


		});*/
	}

	public boolean addProba( Case c, double prob){
		return addProba(new CaseProbability(prob, c));
	}

	public boolean addProba(CaseProbability p){

		if(total + p.proba > 1.1){
			return false;
		}

		probs.add(p);
		total += p.proba;
		sortArray();

		return true;
	}

	public Case getCase(){

		double condition = 0;
		double rnd = Math.random();


		for( int i = 0 ; i < probs.size() ; i++){

			condition += probs.get(i).proba;


			if( rnd < condition ) return probs.get(i).pCase.clone();

		}

		return probs.get(probs.size() - 1).pCase.clone();

	}

	/*
		ArrayList<CaseProbability> temp = new ArrayList<CaseProbability>(probs.size());

		for(int i = 0 ; i < probs.size() ; i++){

			CaseProbability cpt = new CaseProbability(0, null);

			for(int j = i ; j < probs.size() ; j ++){

				if( probs.get(j).proba >= cpt.proba ){
					cpt = probs.get(j);
				}

			}

			temp.add(i, cpt);

		}

		this.probs = temp;
	 */	



}
