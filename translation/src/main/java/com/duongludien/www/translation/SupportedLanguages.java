package com.duongludien.www.translation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.api.services.translate.Translate;
import com.google.api.services.translate.Translate.Languages;
import com.google.api.services.translate.model.LanguagesListResponse;
import com.google.api.services.translate.model.LanguagesResource;

public class SupportedLanguages {
	
	
	/*
	 * Get supported languages
	 * 
	 * @param None
	 * 
	 * @return An ArrayList that contains all supported languages
	 * @return null if error
	 * 
	 * @author Lu-Dien DUONG
	 * @since 25/10/2017 
	 * 
	 * */
	public static ArrayList<LanguageItem> getSupportedLanguages() {
		// Create a translate service
		Translate translateService = Utilities.createTranslateService();
		
		try {
			// List supported languages request
			Languages.List request = translateService.languages().list();
			
			// Get human readable language name (in English)
			request.setTarget("en");
			
			// Execute request
			LanguagesListResponse response = request.execute();
			
			// Export all supported languages to an ArrayList
			List<LanguagesResource> list = response.getLanguages();
			ArrayList<LanguageItem> languages = new ArrayList<LanguageItem>();
			for(LanguagesResource item : list) {
				LanguageItem language = new LanguageItem(item.getLanguage(), item.getName());
				languages.add(language);
			}
			
			return languages;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/*
	 * Get supported languages and export to HashMap
	 * 
	 * @param None
	 * 
	 * @return A HashMap that contains all supported languages
	 * @return null if error
	 * 
	 * @author Lu-Dien DUONG
	 * @since 25/10/2017 
	 * 
	 * */	
	public static HashMap<String, String> getSupportedLanguagesMap() {
		Translate translateService = Utilities.createTranslateService();
		
		try {
			Languages.List request = translateService.languages().list();
			request.setTarget("en");
			
			LanguagesListResponse response = request.execute();
			
			List<LanguagesResource> list = response.getLanguages();
			
			HashMap<String, String> languages = new HashMap<String, String>();
			
			for(LanguagesResource item : list) {
				languages.put(item.getLanguage(), item.getName());
			}
			
			return languages;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
