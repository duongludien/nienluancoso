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
import com.google.api.services.translate.Translate.Translations.List;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;

public class TranslateText {
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
	
	public static ArrayList<TranslationsResource> translateText(ArrayList<String> textList, String source, String target) {
		Translate translateService = createTranslateService();
		Translations translations = translateService.translations();
		
		try {
			List list  = translations.list(textList, target);
			if(source != null)
				list.setSource(source);
			TranslationsListResponse response = list.execute();
			ArrayList<TranslationsResource> result = (ArrayList<TranslationsResource>)response.getTranslations();
			return result;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}