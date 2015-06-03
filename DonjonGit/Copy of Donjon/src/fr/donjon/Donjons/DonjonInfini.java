package fr.donjon.Donjons;

public class DonjonInfini extends DonjonNonLineaire {
	
	int width;
	int height;
	
	public DonjonInfini(int w, int h){
		super(w,h);
		this.width = w;
		this.height = h;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.Donjons.DonjonNonLineaire#checkDonjonFini()
	 */
	@Override
	public void checkDonjonFini(){
		//We go through the array
			for(int i=0; i<smap.length; i++){
				for(int j=0; j<smap[0].length;j++){
					if(!(smap[i][j] == null)){
						if(!smap[i][j].estFinie() || !smap[i][j].allDoorsHaveDestination()){
							return;
						}
					}
				}
			}
			ecouteur.requestNewGame(new DonjonInfini(width,height));
	}
}