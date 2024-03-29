package com.albo.model;

import java.util.List;

public class Folder {

	private String label;
	private String data;
	private String expandedIcon;
	private String collapsedIcon;
	private List<Archivo> listaArchivos;
	
	public Folder(String label, String data, String expandedIcon, String collapsedIcon, List<Archivo> listaArchivos) {
		this.label = label;
		this.data = data;
		this.expandedIcon = expandedIcon;
		this.collapsedIcon = collapsedIcon;
		this.listaArchivos = listaArchivos;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getExpandedIcon() {
		return expandedIcon;
	}

	public void setExpandedIcon(String expandedIcon) {
		this.expandedIcon = expandedIcon;
	}

	public String getCollapsedIcon() {
		return collapsedIcon;
	}

	public void setCollapsedIcon(String collapsedIcon) {
		this.collapsedIcon = collapsedIcon;
	}

	public List<Archivo> getListaArchivos() {
		return listaArchivos;
	}

	public void setListaArchivos(List<Archivo> listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

}
