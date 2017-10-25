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
	public static ArrayList<LanguageItem> getSupportedLanguages() {
		Translate translateService = Utilities.createTranslateService();
		
		try {
			Languages.List request = translateService.languages().list();
			request.setTarget("en");
			
			LanguagesListResponse response = request.execute();
			
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
