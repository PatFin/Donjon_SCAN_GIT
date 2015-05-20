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

import fr.donjon.cases.Case;
import fr.donjon.cases.CaseBloquante;
import fr.donjon.cases.CaseDalle;
import fr.donjon.cases.CaseEscalier;
import fr.donjon.cases.CaseFendue;
import fr.donjon.cases.CaseGlass;
import fr.donjon.cases.CaseHerbe;
import fr.donjon.cases.CaseLave;
import fr.donjon.cases.CaseLiane;
import fr.donjon.cases.CaseMur;
import fr.donjon.cases.CaseRocher;
import fr.donjon.cases.CaseSwitch;
import fr.donjon.cases.CaseTeleportation;
import fr.donjon.cases.CaseVide;
import fr.donjon.cases.CaseWater;


/**
 * @author Baptiste
 *
 */
public class MapFileHandler {

	final static String pathMaps = "files/maps/" ;
	final static String extension = "djmap";
	final static String absolutePath = (new File("")).getAbsolutePath();
	
	/**
	 * Don't use it, its a static class
	 */
	public MapFileHandler() {
	}

	/**
	 * 
	 * Crates the directory for the maps if it does not exist and retreive 
	 * all the files with .djmap extension in a list of File 
	 * 
	 * @return	The list of files corresponding tp a map file
	 */
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
	 * Teste tous les fichiers de salles personnalis�es et les envoie dans la liste de return 
	 * s'ils peuvent fonctionner. Cette m�thode est notamnent utilis�e dans MapGenerator.
	 * @return la liste des descriptions des salles qui peuvent fonctionner dans un donjon
	 */
	public static LinkedList<SalleDescription> getWorkingMaps(){
		LinkedList <SalleDescription> workingMaps = new LinkedList<SalleDescription>();
		LinkedList<File> files = getMapList();
		
		SalleDescription s;
		for(File f: files){
			s = MapFileHandler.getFileToDescription(f);
			if(s != null){
				workingMaps.add(s);
			}
		}
		
		return workingMaps;
	}
	
	/**
	 * 
	 * Allows to get a list of SalleDescription from a list of indexes
	 * 
	 * @param indexes	The list of indexes as an Integer array
	 * @return			The list of SalleDescription
	 */
	public static LinkedList<SalleDescription> getGroupOfMaps(int[] indexes){

		LinkedList<File> files = getMapList();
		LinkedList<SalleDescription> sds = new LinkedList<SalleDescription>();
		LinkedList<SalleDescription> group = new LinkedList<SalleDescription>(); 

		for( File f : files){
			sds.add(getFileToDescription(f));
		}

		for(SalleDescription sd : sds){
			for(int index : indexes){
				if(index == sd.index)group.add(sd);
			}
		}

		return group;
	}

	/**
	 * 
	 * Return the existing indexes of map in the standard directory
	 * 
	 * @return The int table containing the existing indexes
	 */
	public static int[] getExistingIndexes(){

		LinkedList<File> files = getMapList();
		LinkedList<SalleDescription> sds = new LinkedList<SalleDescription>();

		for(File f : files){
			if( getFileToDescription(f)!= null) sds.add(getFileToDescription(f));
		}

		int[] idxs = new int[sds.size()];

		for(SalleDescription sd : sds){
			idxs[sds.indexOf(sd)] = sd.index;
		}

		return idxs;
	}

	/**
	 * 	Returns a SalleDescription from a File
	 * 
	 * @param f	The file to read
	 * @return	The SalleDescription of the file or null if the file is not readable
	 */
	public static SalleDescription getFileToDescription(File f){

		try{
			int index = Integer.parseInt( f.getName().split("\\.")[0] );

			return getSalleDescriptionFromFile(index);
		}catch(Exception e){
			System.out.println("This file is not a readable map file ("+f.getName() + ")");
			return null;
		}
		
	}

	/**
	 * 
	 * Create a file from a SalleDescription and write it, if the file already exists and erase is false,
	 * a new index will be set
	 * 
	 * @param sd		The description to write on the file
	 * @param erase		If the file has to be erased if it exists
	 * @return			Returns the index of the written map (can be different from the one asked)
	 */
	public static int createMapFile(SalleDescription sd, boolean erase){


		File f = new File(getMapFilePath(sd.index));
		FileWriter fr;

		if(!erase && f.exists()){

			int newIndex = 0;
			int[] idxs = getExistingIndexes();

			for(int index : idxs){
				if( index >= newIndex )newIndex = index;
			}

			sd.setIndex(newIndex+1);

			System.out.println("This map ("+sd.name+") already exists, the new index will be "+(newIndex+1));

			try {
				f = new File(getMapFilePath(sd.index));
				f.createNewFile();
				System.out.println("Writting new map file "+sd.name);
			} catch (IOException e1) {
				System.out.println("Error creating file map "+sd.name);
				return -1;
			}

		}else if (erase && f.exists()){
			f.delete();
			try {
				f.createNewFile();
				System.out.println("Overwritting map file "+sd.name);
			} catch (IOException e1) {
				System.out.println("Error erasing map "+sd.name);
				return -1;
			}

		}else if (!f.exists()){
			try {
				f.createNewFile();
				System.out.println("Creating map file "+sd.name);
			} catch (IOException e2) {
				System.out.println("Error creating map file "+sd.name);
				return -1;
			}
		}

		//At this point the file has been created

		try {
			fr = new FileWriter(getMapFilePath(sd.index));
			fr.write(getHeadOfMapFile(sd.name, sd.matrix, sd.index));
			fr.write(getMatrixToString(sd.matrix));
			fr.close();

			System.out.println("Map written "+sd.name);
			return sd.index;

		} catch (IOException e3) {
			System.out.println("Error writing map "+sd.name);
			return -1;
		}

	}

	/**
	 * Returns a SalleDescription object from the file with the corresponding index
	 * 
	 * @param index	The index of the file to be read
	 * @return The description of the file
	 */
	public static SalleDescription getSalleDescriptionFromFile(int index){

		File f = new File(getMapFilePath(index));
		String file = null;
		Scanner sc = null;

		try {
			sc = new Scanner(f);
			file = sc.nextLine();
			System.out.println("Reading "+getMapFilePath(index));
		} catch (FileNotFoundException e) {
			System.out.println("Error reading map  "+getMapFilePath(index));
		}

		sc.close();

		String[] properties = file.split(";")[0].split("-");

		String[] matLin = file.split(";")[1].split("-");

		SalleDescription sd = new SalleDescription(matLin, properties[2], properties[3], properties[0], properties[1]);

		System.out.println("Map read :\n"+sd.toString());

		return sd;
	}

	/**
	 * 
	 * Create the head of the file, it corresponds to the caracteristics
	 * of a SalleDescription
	 * 
	 * @param name	The name of the map
	 * @param mat	The matrix of Case 
	 * @param index	The unique index of the map
	 * @return		The head as a String
	 */
	private static String getHeadOfMapFile(String name, Case[][] mat, int index){

		return 	name + "-" + 
				index + "-" +
				mat.length + "-" + 
				mat[0].length + ";";

	}

	/**
	 * 
	 * Change the matrix of Cases into a String description to write on a file
	 * 
	 * @param mat	The matrix to transform
	 * @return		The String description
	 */
	private static String getMatrixToString(Case[][] mat){

		String ms = "";

		for(int y = 0 ; y < mat[0].length ; y++){
			for(int x = 0 ; x < mat.length ; x++ ){
				ms += getIndexFromCase(mat[x][y]) + "-";
			}
		}

		return ms;

	}

	/**
	 * 
	 * Return the relative path to a file map
	 * ex: "files/maps/01.djmap" 
	 * 
	 * @param index	The specified index
	 * @return	The path as a String
	 */
	private static String getMapFilePath(int index){

		return pathMaps + index + "."  + extension;
	}

	/**
	 * 
	 * Creates a list containing the different cases of the game
	 * 
	 * @return	The LinkedList containing all the Case
	 */
	private static LinkedList<Case> getCasesList(){

		LinkedList<Case> listCases = new LinkedList<Case>();
		
		listCases.add(new CaseDalle());
		listCases.add(new CaseEscalier());
		listCases.add(new CaseFendue());
		listCases.add(new CaseGlass());	
		listCases.add(new CaseHerbe());
		listCases.add(new CaseMur());
		listCases.add(new CaseLiane());
		listCases.add(new CaseRocher());
		listCases.add(new CaseSwitch());
		listCases.add(new CaseTeleportation());
		listCases.add(new CaseLave());
		listCases.add(new CaseWater());
		listCases.add(new CaseBloquante());	
		listCases.add(new CaseVide());

		
		return listCases;

	}

	/**
	 * 
	 * Associates a unique index to a Case, it creates the list of available Case
	 * and uses the index from this list to get the unique index.
	 * 
	 * @param c		The Case we want the corresponding index of 
	 * @return		The index associated to the Case
	 */
	private static int getIndexFromCase(Case c){

		String name = c.getClass().getName();

		LinkedList<Case> list = getCasesList();

		for(int i = 0 ; i < list.size() ; i++ ){

			if(name == list.get(i).getClass().getName() )return i;

		}

		return -1;

	}

	/**
	 * 
	 * It returns the Case associated to an index
	 * 
	 * @param index	The index of the case
	 * @return		The Case associated to the index, return by default a Case_void if the Case is null
	 */
	public static Case getCaseFromIndex(int index){

		LinkedList<Case> list = getCasesList();

		Case c = list.get(index).clone();

		return c == null ? new CaseVide() : c;
	}
}
