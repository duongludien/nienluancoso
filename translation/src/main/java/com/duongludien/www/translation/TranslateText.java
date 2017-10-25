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
import com.google.api.services.translate.Translate.Translations;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;

public class TranslateText {
	
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
	
	/*
	 * Translate text
	 * 
	 * @param sourceText source text for translating
	 * @param sourceLang source language of source text
	 * @param targetLang target language for translating
	 * 
	 * @return translated text
	 * 
	 * @author Lu-Dien DUONG
	 * @since 25/10/2017
	 * 
	 * */
	public static String translateText(String sourceText, String sourceLang, String targetLang) {
		// Split by line and add all lines to ArrayList
		String strList[] = sourceText.split("\n");
		ArrayList<String> q = new ArrayList<String>();
		for(String item : strList) {
			q.add(item);
		}
		
		// Translate service
		Translate translateService = createTranslateService();
		Translations translations = translateService.translations();
		
		try {
			Translate.Translations.List request  = translations.list(q, targetLang);
			
			// Detect language or no
			if(sourceLang != null)
				request.setSource(sourceLang);
			
			// Execute translate
			TranslationsListResponse response = request.execute();
			ArrayList<TranslationsResource> result = (ArrayList<TranslationsResource>)response.getTranslations();
			
			// Build result
			StringBuilder builder = new StringBuilder("");
			String newLangDetector = "";		// For detecting multiple languages
			for(TranslationsResource item : result) {
				// If source language is not defined, get detected source language
				if(sourceLang == null) {
					String detectedLang = item.getDetectedSourceLanguage();
					if(!detectedLang.equals(newLangDetector)) {
						newLangDetector = detectedLang;
						builder.append("Detected language: " + detectedLang + "\n");
					}
				}
				
				builder.append(item.getTranslatedText() + "\n");
			}
			
			// Return only one String with multiple line
			return builder.toString();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// Return null if error
		return null;
	}
}