package com.duongludien.www.translation;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.TranslateRequestInitializer;

public class Utilities {
	/*
	 * Create translate service
	 * 
	 * @param None
	 * 
	 * @return Translate service
	 * 
	 * @author Lu-Dien DUONG
	 * @since 25/10/2017
	 * 
	 * */
	public static Translate createTranslateService() {
		try {
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			JsonFactory jsonFactory = Utils.getDefaultJsonFactory();
			TranslateRequestInitializer translateRequestInitializer = new TranslateRequestInitializer("AIzaSyA8gG0zwQ00opVgu6bKx5iaLcTt3zDCUmw");
			
			Translate.Builder builder = new Translate.Builder(httpTransport, jsonFactory, null);
			builder.setApplicationName("Translator");			
			builder.setTranslateRequestInitializer(translateRequestInitializer);
			
			Translate translateService = builder.build();
			
			return translateService;
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<String> splitByLine(String text) {
		String strList[] = text.split("\n");
		ArrayList<String> q = new ArrayList<String>();
		for(String item : strList) {
			q.add(item);
		}
		
		return q;
	}	
}
