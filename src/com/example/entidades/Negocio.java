package com.example.entidades;

public class Negocio {

	public long id;
    public String nombrecorto;
    public String nombrelargo;
    public String imagen;
    public String detalle;
    public String direccion;
    public String horario;
    public int subcategoriaId;
 
    
    public Negocio() { }
 
    public Negocio(String nombrecorto, String nombrelargo, String imagen,
    		String detalle, String direccion, String horario, int subcategoriaId) {        
    	this.nombrecorto = nombrecorto;
        this.nombrelargo = nombrelargo;
        this.imagen = imagen;
        this.detalle = detalle;
        this.direccion = direccion;
        this.horario = horario;
        this.subcategoriaId = subcategoriaId;
    }
    
}
