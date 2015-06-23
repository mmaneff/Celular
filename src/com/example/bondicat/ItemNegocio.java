package com.example.bondicat;

import android.graphics.drawable.Drawable;

public class ItemNegocio {


	 protected long id;
	  protected Drawable imagen;
	  protected String nombre;
	  protected String cercania;
	  protected String domicilio;
		
	  
	  public ItemNegocio() {
	    this.nombre = "";
	    this.imagen = null;
	    this.cercania="";
	  }
	 
	  public ItemNegocio(long id, String nombre, Drawable imagen,String cercania,String domicilio) {
	    this.id = id;
	    this.nombre = nombre;
	    this.imagen=imagen;
	    this.cercania=cercania;
	    this.domicilio=domicilio;
	  }

	  
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
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

	public String getCercania() {
		return cercania;
	}

	public void setCercania(String cercania) {
		this.cercania = cercania;
	}
	  

}
