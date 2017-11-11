package com.duongludien.translation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.DetectionsListResponse;
import com.google.api.services.translate.model.DetectionsResourceItems;

public class DetectLanguages {


	/*
	 * Detect language
	 * 
	 * @param sourceText Text to detect
	 * 
	 * @return a string that contains all languages which is detected
	 * @return null if fail
	 * 
	 * @author Lu-Dien DUONG
	 * @since 25/10/2017
	 * 
	 * */
	public static String detectLanguages(String sourceText) {
		// Split input text by line
		ArrayList<String> q = Utilities.splitByLine(sourceText);
		
		// Get all supported languages to show human readable language name in result
		HashMap<String, String> languages = SupportedLanguages.getSupportedLanguagesMap();
		
		// Create a translate service for detecting
		Translate translateService = Utilities.createTranslateService();
		Translate.Detections detections = translateService.detections();
		
		try {
			// Detection request
			Translate.Detections.List request = detections.list(q);
			
			// Execute detection request
			DetectionsListResponse response = request.execute();
			List<List<DetectionsResourceItems>> list = response.getDetections();
			
			// Build result
			StringBuilder builder = new StringBuilder("Detected languages:\n");	
			ArrayList<String> printedLanguages = new ArrayList<String>();
			for(List<DetectionsResourceItems> item1 : list) {
				for(DetectionsResourceItems item2 : item1) {
					if(!Utilities.isIn(item2.getLanguage(), printedLanguages)) {
						builder.append(languages.get(item2.getLanguage()) + "\n");
						printedLanguages.add(item2.getLanguage());
					}
				}
			}
			
			// Return only one string 
			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
