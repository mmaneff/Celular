package com.example.bondicat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

public class ItemLista {
	 protected long id;
	  protected Drawable rutaImagen;
	  protected String nombre;
	  protected Drawable fondo;
	  protected Color color;
	
	  
	  public ItemLista() {
	    this.nombre = "";
	    this.rutaImagen = null;
	    this.fondo=null;
	  }
	 
	  public ItemLista(long id, String nombre, Drawable fondo, Color color) {
	    this.id = id;
	    this.nombre = nombre;
	    this.fondo=fondo;
	    this.color=color;
	  }
	  public ItemLista(long id, String nombre, Drawable fondo) {
		    this.id = id;
		    this.nombre = nombre;
		    this.fondo=fondo;
		  }
	 
	  public ItemLista(long id, String nombre,Drawable rutaImagen,Drawable fondo) {
	    this.id = id;
	    this.nombre = nombre;
	    this.rutaImagen = rutaImagen;
	    this.fondo=fondo;
	  }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Drawable getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(Drawable rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public Drawable getFondo() {
		return fondo;
	}

	public void setFondo(Drawable fondo) {
		this.fondo = fondo;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	

}
