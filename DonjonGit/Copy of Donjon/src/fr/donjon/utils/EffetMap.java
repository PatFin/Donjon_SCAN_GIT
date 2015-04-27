/**
 * 
 */
package fr.donjon.utils;

import java.util.HashMap;


/**
 * 
 * Cette classe représente une liste contenant des clés de type String associés a un Effet
 * 
 * @author Baptiste
 *
 * @param <K>
 * @param <V>
 */
public class EffetMap<K, V> extends HashMap<String, Effet> {

	/**
	 * Permet de créer une map vide
	 */
	public EffetMap(){
		super();
	}


	/**
	 * Permet d'ajouter un effet à la liste en spécifiant la clé associée,
	 * les ajout sont gérés selon les attributs de l'effet, ainsi les effets cummulables
	 * sont ajoutés et les non cummulables sont remplacés. 
	 * 
	 * @param key 	La clé associé à l'effet
	 * @param e		L'effet asocié
	 */
	public void addEffect(String key, Effet e){

		Effet e1 = get(key);
		
		if(e1 == null){
			put(key, e);
			return;
		}

		//IF TIMED
		if(e1.timed){
			if(e1.actif){
				if(e1.cummulable){
					//Do nothing
				}
				else {
					//Do nothing
				}
			}
			//IF NOT ACTIVE
			else{
				if(e1.cummulable){
					//ADD
					Effet sum = e1.sum(e);
					//TODO replace(key, sum.initialiseWithParametersOf(e));
				}
				else {
					//Replace
					//TODO replace(key, e);
				}
			}
		}
		//IF NOT TIMED
		else {
			if(e1.actif){
				if(e1.cummulable){
					//ADD
					Effet sum = e1.sum(e);
					//TODO replace(key, sum.initialiseWithParametersOf(e));
				}
				else {
					//Replace
					//TODO replace(key, e);
				}
			}
			//IF NOT ACTIVE
			else{
				if(e1.cummulable){
					//Nothing, no relevant case
				}
				else {
					//Replace
					//TODO replace(key, e);
				}
			}
		}
	}

	
	/**
	 * Supprime l'entrée de la liste associé a la clé
	 * 
	 * @param key La clé a supprimer de la liste
	 */
	public void removeEffect(String key){

		remove(key);

	}

	/**
	 * Renvoie la somme des effets de la liste,
	 * les évènements inactifs ne sont pas sommés à moins d'avoir
	 * leur attribut ignoreEtat valant "true".
	 * 
	 * @return La somme des effets
	 */
	public Effet getSum(){

		Effet e1 = new Effet(null,0,0,0,0);

		//System.out.println("//////////SUM/////////");

		for(Effet  e : values() ){
			if( e.ignoreEtat || e.actif ) {
				e1 = e1.sum(e);
			//	System.out.println("Summing: "+e.toString());
			}
		}
		
		//System.out.println("Total: "+e1.toString());
		

		return e1;
	}

}
