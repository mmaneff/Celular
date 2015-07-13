package com.example.entidades;

public class SubCategoria {

	public int id;
	public String nombre;
	public int categoriaId;
	
	public SubCategoria() {}
	
	public SubCategoria(String nombre, int categoriaId) {
		this.nombre = nombre;
		this.categoriaId = categoriaId;
	}
	
}
