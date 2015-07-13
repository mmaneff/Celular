package com.example.entidades;

public class Categoria {

	public int id;
	public String nombre;
	public String color;
	public String imagen;
	
	public Categoria() {}
	
	public Categoria(String nombre, String color, String imagen) {
		this.nombre = nombre;
		this.color = color;
		this.imagen = imagen;
	}
	
}
