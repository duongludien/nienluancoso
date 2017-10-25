package com.duongludien.www.translation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.DetectionsListResponse;
import com.google.api.services.translate.model.DetectionsResourceItems;

public class DetectLanguages {
	private static boolean isIn(String str, ArrayList<String> arr) {
		for(String string : arr) {
			if(str.equals(string))
				return true;
		}
		return false;
	}
	
	public static String detectLanguages(String sourceText) {
		ArrayList<String> q = Utilities.splitByLine(sourceText);
		HashMap<String, String> languages = SupportedLanguages.getSupportedLanguagesMap();
		
		Translate translateService = Utilities.createTranslateService();
		Translate.Detections detections = translateService.detections();
		try {
			Translate.Detections.List request = detections.list(q);
			DetectionsListResponse response = request.execute();
			List<List<DetectionsResourceItems>> list = response.getDetections();
			
			StringBuilder builder = new StringBuilder("Detected languages:\n");	
			ArrayList<String> printedLanguages = new ArrayList<String>();
			for(List<DetectionsResourceItems> item1 : list) {
				for(DetectionsResourceItems item2 : item1) {
					if(!isIn(item2.getLanguage(), printedLanguages)) {
						printedLanguages.add(item2.getLanguage());
						builder.append(languages.get(item2.getLanguage()) + "\n");
					}
				}
			}
			
			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
