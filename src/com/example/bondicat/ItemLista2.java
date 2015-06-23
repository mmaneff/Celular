package com.example.bondicat;

import android.graphics.drawable.Drawable;

public class ItemLista2 {
	 protected long id;
	  protected Drawable rutaImagen;
	  protected String nombre;
	  protected String colorFondo;
	
	  
	  public ItemLista2() {
	    this.nombre = "";
	    this.rutaImagen = null;
	    this.colorFondo="";
	  }
	 
	  public ItemLista2(long id, String nombre, String colorFondo) {
	    this.id = id;
	    this.nombre = nombre;
	    this.colorFondo = colorFondo;
	  }
	 
	  public ItemLista2(long id, String nombre,Drawable rutaImagen,String colorFondo) {
	    this.id = id;
	    this.nombre = nombre;
	    this.rutaImagen = rutaImagen;
	    this.colorFondo = colorFondo;
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

	public String getColorFondo() {
		return colorFondo;
	}

	public void setColorFondo(String colorFondo) {
		this.colorFondo = colorFondo;
	}

}
