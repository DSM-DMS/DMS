package com.dms.planb.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.boxfox.dms.algorithm.SHA256;

public class ProfileImage {
	private static String directoryPath = "Profile Images";
	private static StringBuilder data;
	
	private static String hashedId;
	
	private static File file;
	
	private static FileReader fr;
	private static FileWriter fw;
	
	public static void setProfileImage(String id, String data) {
		hashedId = SHA256.encrypt(id);
		
		file = new File(directoryPath.concat(hashedId).concat(".txt"));
		
		try {
			fw = new FileWriter(file);
			/*
			 * File does not exist, FileWriter class creates file automatically.
			 */
			
			fw.write(data);
			fw.close();
		} catch (IOException e) { }
	}
	
	public static String getProfileImage(String id) {
		hashedId = SHA256.encrypt(id);
		
		file = new File(directoryPath.concat(hashedId).concat(".txt"));

		try {
			fr = new FileReader(file);
			
			data = new StringBuilder();
			data.append((char) fr.read());
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) { }
		
		return data.toString();
	}
}
