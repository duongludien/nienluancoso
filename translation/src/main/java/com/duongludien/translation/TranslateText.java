package com.duongludien.translation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.api.services.translate.Translate;
import com.google.api.services.translate.Translate.Translations;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;

public class TranslateText {
	
	/*
	 * Translate text
	 * 
	 * @param sourceText source text for translating
	 * @param sourceLang source language of source text
	 * @param targetLang target language for translating
	 * 
	 * @return translated text if success
	 * @return null if error
	 * 
	 * @author Lu-Dien DUONG
	 * @since 25/10/2017
	 * 
	 * */
	public static String translateText(String sourceText, String sourceLang, String targetLang) {
		// Split sourceText by line
		ArrayList<String> q = Utilities.splitByLine(sourceText);
		
		// Translate service
		Translate translateService = Utilities.createTranslateService();
		Translations translations = translateService.translations();
		
		try {
			// Translate request
			Translate.Translations.List request  = translations.list(q, targetLang);
			
			// Detect language or no
			if(sourceLang != null)
				request.setSource(sourceLang);
			
			// Execute translate
			TranslationsListResponse response = request.execute();
			ArrayList<TranslationsResource> result = (ArrayList<TranslationsResource>)response.getTranslations();
			
			// Build result
			StringBuilder builder = new StringBuilder("");
			HashMap<String, String> languages = SupportedLanguages.getSupportedLanguagesMap();
			String previousLangDetector = "";		// For detecting multiple languages
			
			// Show each line of result
			for(TranslationsResource item : result) {
				
				// If source language is not defined, get detected source language
				if(sourceLang == null) {
					String detectedLang = item.getDetectedSourceLanguage();
					if(!detectedLang.equals(previousLangDetector)) {
						previousLangDetector = detectedLang;
						builder.append("Detected language: " + languages.get(detectedLang) + "\n");
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