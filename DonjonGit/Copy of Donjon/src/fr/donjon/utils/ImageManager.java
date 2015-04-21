package fr.donjon.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageManager {

	final static String path = "Images/";
	static HashMap<String,Image> map = new HashMap<String, Image>();


	public ImageManager() {
	}

	public static Image getImage(String subpath, String who){

		Image im = null ;

		if(map.containsKey(subpath)){
			System.out.println(subpath + " already loaded by "+who);
			return map.get(subpath);
		}

		try {
			im = ImageIO.read(new File(path+subpath));
			map.put(subpath, im);
		} catch (IOException e) {
			System.out.println("Failed to read the image "+subpath);
			System.exit(0);
		} 

		return im;
	}



	public static void list(){

		System.out.println(map.toString());

	}

}
