package com.albo.model;

public class FileAux {

	private String data;
	private int nivel;

	
	public FileAux(String data, int nivel) {
		this.data = data;
		this.nivel = nivel;
	}

	public FileAux() {
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
}
