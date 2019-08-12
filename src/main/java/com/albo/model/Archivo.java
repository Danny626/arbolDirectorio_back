package com.albo.model;

public class Archivo {

	private String label;
	private String icon;
	private String data;

	public Archivo(String label, String icon, String data) {
		this.label = label;
		this.icon = icon;
		this.data = data;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
