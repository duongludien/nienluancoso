package com.duongludien.www.translation;


public class LanguageItem {
	private String code;
	private String name;
	
	public LanguageItem() {
		super();
	}

	public LanguageItem(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
