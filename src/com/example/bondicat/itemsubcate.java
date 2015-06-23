package com.example.bondicat;

import android.graphics.drawable.Drawable;

public class itemsubcate {
	
	 protected long id;
	  protected Drawable imagen;
	  protected String nombre;
	
	  
	  public itemsubcate() {
	    this.nombre = "";
	    this.imagen = null;
	  }
	 
	  public itemsubcate(long id, String nombre, Drawable imagen) {
	    this.id = id;
	    this.nombre = nombre;
	    this.imagen=imagen;
	  }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Drawable getImagen() {
		return imagen;
	}

	public void setImagen(Drawable imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	 


}
