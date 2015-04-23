/**
 * 
 */
package fr.donjon.utils;

import java.util.HashMap;



public class EffetMap<K, V> extends HashMap<String, Effet> {


	public EffetMap(  ){
		super();
	}



	public void addEffect(String key, Effet e){

		if(!containsKey(key)){
			this.put(key, e);
		}

		else { //Contient ET finit
			if( !get(key).enCours  && !e.constant)replace(key, e);
			if( !get(key).enCours  &&  e.constant){
				Effet sum = get(key).sum(e);
				replace(key, new Effet(sum.vie, sum.def, sum.vit, sum.atk, get(key).duree,true));
			}
			
		}

	}

	public void removeEffect(String key){

		remove(key);

	}

	public Effet getSum(){

		Effet e1 = new Effet(0,0,0,0,false );

		
		for(Effet  e : values() ){
				if(e.enCours || e.constant)e1 = e1.sum(e);
		}
		

		return e1;
	}

}
