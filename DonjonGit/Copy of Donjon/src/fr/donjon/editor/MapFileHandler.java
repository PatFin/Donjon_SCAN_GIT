/**
 * 
 */
package fr.donjon.editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_dalle_sol;
import fr.donjon.classes.cases.Case_escalier;
import fr.donjon.classes.cases.Case_fendue_sol;
import fr.donjon.classes.cases.Case_herbe;
import fr.donjon.classes.cases.Case_mur;
import fr.donjon.classes.cases.Case_rocher;
import fr.donjon.classes.cases.Case_void;
import fr.donjon.utils.Orientation;

/**
 * @author Baptiste
 *
 */
public class MapFileHandler {

	final static String pathMaps = "files/maps/" ;
	final static String extension = "djmap";

	/**
	 * 
	 */
	public MapFileHandler() {
		// TODO Auto-generated constructor stub
	}

	public static LinkedList<File> getMapList(){

		File f = new File(pathMaps);
		LinkedList<File> list = new LinkedList<File>();

		if(!f.exists()){
			System.out.println("Directory created");
			f.mkdirs();
		}

		for( File fl : f.listFiles()){
			if(fl.getName().endsWith(extension)){
				list.add(fl);
			}
		}

		return list;

	}

	

	/**
	 * 
	 * @param name
	 * @param mat
	 * @param index
	 * @param erase
	 * @return Returns false if file hasn't been written
	 */
	public static boolean createMapFile(SalleDescription sd, boolean erase){

		File f = new File(getMapFilePath(sd.index));
		FileWriter fr;

		if(!erase){
			System.out.println("This map ("+sd.name+") already exits, please change its name.");
			return false;
		}else if (f.exists()){
			f.delete();
			try {
				f.createNewFile();
				System.out.println("Overwritting map file "+sd.name);
			} catch (IOException e1) {
				System.out.println("Error erasing map "+sd.name);
				return false;
			}

		}else if (!f.exists()){
			try {
				f.createNewFile();
				System.out.println("Creating map file "+sd.name);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				System.out.println("Error creating map file "+sd.name);
				return false;
			}
		}

		//At this point the file has been created

		try {
			fr = new FileWriter(getMapFilePath(sd.index));
			fr.write(getHeadOfMapFile(sd.name, sd.matrix, sd.index));
			fr.write(getMatrixToString(sd.matrix));
			fr.close();

			System.out.println("Map written "+sd.name);
			return true;

		} catch (IOException e3) {
			// TODO Auto-generated catch block
			System.out.println("Error writing map "+sd.name);
			return false;
		}

	}

	public static SalleDescription getSalleDescriptionFromFile(int index){
		
		File f = new File(getMapFilePath(index));
		String file = null;
		Scanner sc = null;
		
		try {
			sc = new Scanner(f);
			file = sc.nextLine();
			System.out.println("Reading "+getMapFilePath(index));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error reading map  "+getMapFilePath(index));
		}
		
		sc.close();
		
		String[] properties = file.split(";")[0].split("-");
		
		String[] matLin = file.split(";")[1].split("-");
		
		SalleDescription sd = new SalleDescription(matLin, properties[2], properties[3], properties[0], properties[1]);
		
		System.out.println("Map read :\n"+sd.toString());
		
		return sd;
	}
	
	private static String getHeadOfMapFile(String name, Case[][] mat, int index){

		return 	name + "-" + 
				index + "-" +
				mat.length + "-" + 
				mat[0].length + ";";

	}

	private static String getMatrixToString(Case[][] mat){

		String ms = "";

		for(int y = 0 ; y < mat[0].length ; y++){
			for(int x = 0 ; x < mat.length ; x++ ){
				ms += getIndexFromCase(mat[x][y]) + "-";
			}
		}

		return ms;

	}

	private static String getMapFilePath(int index){

		return pathMaps + index + "."  + extension;
	}

	private static LinkedList<Case> getCasesList(){

		LinkedList<Case> listCases = new LinkedList<Case>();

		listCases.add(new Case_dalle_sol());
		listCases.add(new Case_escalier());
		listCases.add(new Case_fendue_sol());
		listCases.add(new Case_herbe());
		listCases.add(new Case_mur());
		listCases.add(new Case_rocher());
		listCases.add(new Case_void());

		return listCases;

	}

	private static int getIndexFromCase(Case c){

		String name = c.getClass().getName();

		LinkedList<Case> list = getCasesList();

		for(int i = 0 ; i < list.size() ; i++ ){

			if(name == list.get(i).getClass().getName() )return i;

		}

		return -1;

	}

	public static Case getCaseFromIndex(int index){

		LinkedList<Case> list = getCasesList();

		Case c = list.get(index).clone();

		return c == null ? new Case_void() : c;
	}
}
